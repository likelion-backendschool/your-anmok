package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.entity.Bookmark;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.repository.BookmarkRepository;
import com.lion.youranmok.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final BookmarkRepository bookmarkRepository;


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
    public Page<CategorySortingDto> findByTagNameContaining(int page, String keyword, int userId) {

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
    public Page<CategorySortingDto> getListByPaging(int page, int userId) {

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

        List<CategorySortingDto> categories = new ArrayList<>();

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
                    .tagName(category.getTagName()).build();

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

        return dtos;
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
            return findByTagNameContaining(page, keyword, userId);
        }
        else {
            return getListByPaging(page, userId);
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
        System.out.println(tagName);
        return categoryRepository.getCategoryByTagName(tagName);
    }


}
