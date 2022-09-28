package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.entity.Bookmark;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.repository.BookmarkRepository;
import com.lion.youranmok.category.repository.CategoryRepository;
import com.lion.youranmok.place.entity.PlaceImage;
import com.lion.youranmok.place.repository.PlaceCategoryMapRepository;
import com.lion.youranmok.place.repository.PlaceImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final BookmarkRepository bookmarkRepository;

    private final PlaceCategoryMapRepository placeCategoryMapRepository;

    private final PlaceImageRepository placeImageRepository;


    @Override
    public List<CategoryDto> findAll() {

        List<CategoryDto> categories;

        categories = categoryRepository.findAll().stream().map(i -> {
            CategoryDto dto = entityToDto(i);
            return dto;
        }).collect(Collectors.toList());

        return categories;
    }

    @Override
    public Page<CategorySortingDto> getListByKeyword(int page, String keyword, int userId) {

        Pageable pageable = PageRequest.of(page, 9);

        List<CategorySortingDto> categorySortingDtos;

        if (userId > 0) {
            categorySortingDtos = categoryRepository.getCategoriesByUserContainingKeyword(userId, keyword);

        } else {
            categorySortingDtos = categoryRepository.getSortingCategoriesContainingKeyword(keyword);
        }

        Page<CategorySortingDto> categories = getCategorySortingDtos(pageable, categorySortingDtos, userId);

        return categories;
    }

    @Override
    public Page<CategorySortingDto> getList(int page, int userId) {

        Pageable pageable = PageRequest.of(page, 9);

        List<CategorySortingDto> categorySortingDtos;

        if (userId > 0) {
            categorySortingDtos = categoryRepository.getCategoriesByUser(userId);
        }
        else {
            categorySortingDtos = categoryRepository.getSortingCategories();
        }

        Page<CategorySortingDto> categories = getCategorySortingDtos(pageable, categorySortingDtos, userId);

        return categories;

    }

    @Override
    public List<CategorySortingDto> getRecommendCategories(int userId) {

        List<CategorySortingDto> categories;

        if (userId > 0) {
            categories = categoryRepository.getSortingCategories();
            categories = categories.stream().sorted(Comparator.comparing(CategorySortingDto::getBookmarkCnt).reversed()).collect(Collectors.toList());
        } else {
            categories = categoryRepository.getSortingCategories();
            categories = categories.stream().sorted(Comparator.comparing(CategorySortingDto::getTotalPlaceCnt).reversed()).collect(Collectors.toList());
        }

        if (categories.size() > 6) {
            categories = categories.subList(0, 6);
        }

        List<CategorySortingDto> dtos = new ArrayList<>();

        categories.stream().forEach(category -> {
            CategorySortingDto dto = CategorySortingDto.builder()
                    .id(category.getId())
                    .tagName(category.getTagName())
                    .bookmarkCnt(category.getBookmarkCnt())
                    .imagePath(category.getImagePath())
                    .totalPlaceCnt(category.getTotalPlaceCnt())
                    .build();

            if (userId > 0) {
                Optional<Bookmark> result = bookmarkRepository.findBookmarkByUserIdAndCategoryId(userId, dto.getId());

                if (result.isPresent()) {
                    dto.setBookmark(true);
                } else {
                    dto.setBookmark(false);
                }
            }

            dtos.add(dto);

        });

        setImages(dtos);

        return dtos;
    }

    private void setImages(List<CategorySortingDto> dtos) {
        dtos.stream().forEach(dto -> {

            List<Integer> placeIdList = placeCategoryMapRepository.getPlaceByCategoryId(dto.getId());

            for (int i = 0; i < placeIdList.size(); i++) {

                List<PlaceImage> placeImages = placeImageRepository.getPlaceImagesByPlaceId(placeIdList.get(i));

                if (placeImages.size() != 0) {
                    dto.setExistImage(true);

                    int index = (int) (Math.random() * (placeImages.size() - 1));

                    dto.setImagePath("/placeimg/" + placeImages.get(index).getFilePath());

                    break;
                }

            }

        });
    }

    @Override
    public List<CategoryDto> getBookmarkCategoriesByUser(int userId) {

        List<Bookmark> bookmarks = bookmarkRepository.findBookmarkByUserId(userId);
        List<Category> categories = new ArrayList<>();

        bookmarks.stream().forEach(bookmark -> {

            Optional<Category> category = categoryRepository.findById(bookmark.getCategoryId());

            if (category.isPresent()) {
                categories.add(category.get());
            }

        });

        List<CategoryDto> result = categories.stream().map(category -> entityToDto(category)).collect(Collectors.toList());

        return result;
    }

    @Override
    public Page<CategorySortingDto> getCategories(int page, String keyword, int userId) {

        if (keyword != null) {
            return getListByKeyword(page, keyword, userId);
        }
        else {
            return getList(page, userId);
        }

    }

    @Override
    public Integer addCategory(CategoryDto categoryDto) {

        String tagName = (categoryDto.getTagName()).replaceAll("#", "");

        categoryDto.setTagName("#" + tagName);

        Optional<Category> result = categoryRepository.findByTagName(categoryDto.getTagName());

        if (result.isPresent()) {
            return 0;
        }

        Category category = categoryRepository.save(dtoToEntity(categoryDto));

        return category.getId();

    }

    private Page<CategorySortingDto> getCategorySortingDtos(Pageable pageable, List<CategorySortingDto> categorySortingDtos, int userId) {

        if (userId > 0) {
            categorySortingDtos.stream().forEach(dto -> {

                Optional<Bookmark> result = bookmarkRepository.findBookmarkByUserIdAndCategoryId(userId, dto.getId());

                if (result.isPresent()) {
                    dto.setBookmark(true);
                } else {
                    dto.setBookmark(false);
                }


            });
        }

        setImages(categorySortingDtos);

        categorySortingDtos = categorySortingDtos.stream().sorted(Comparator.comparing(CategorySortingDto::getBookmarkCnt).reversed()).collect(Collectors.toList());

        int start= (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > categorySortingDtos.size() ? categorySortingDtos.size() : (start + pageable.getPageSize());

        Page<CategorySortingDto> categories = new PageImpl<>(categorySortingDtos.subList(start, end), pageable, categorySortingDtos.size());
        return categories;
    }

    @Override
    public Optional<Category> findByTagName(String tagName){
        return categoryRepository.findByTagName(tagName);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public Category getCategoryByTagName(String tagName) {
        String newtagName = tagName.replaceAll("#", "");
        tagName = "#"+newtagName;
        return categoryRepository.getCategoryByTagName(tagName);
    }


}
