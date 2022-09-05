package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.dto.CategorySortingDto;
import com.lion.youranmok.category.entity.Bookmark;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.repository.BookmarkRepository;
import com.lion.youranmok.category.repository.CategoryRepository;
import com.lion.youranmok.gathering.dto.GatheringPreviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final BookmarkRepository bookmarkRepository;


    @Override
    public List<CategoryDto> findAll() {

        List<CategoryDto> categories = new ArrayList<>();

        categories = categoryRepository.findAll().stream().map(i -> {
            CategoryDto dto = entityToDto(i);
            return dto;
        }).collect(Collectors.toList());


        return categories;
    }

    // TODO 북마크 체크하는 함수 추출 리팩토링 필요

    @Override
    public Page<CategorySortingDto> findByTagNameContaining(int page, String keyword) {

        Pageable pageable = PageRequest.of(page, 9);

        List<CategorySortingDto> categorySortingDtos = categoryRepository.getSortingCategoriesContainingKeyword(keyword);

        Page<CategorySortingDto> categories = getCategorySortingDtos(pageable, categorySortingDtos);

        return categories;
    }

    @Override
    public Page<CategorySortingDto> getListByPaging(int page) {

        Pageable pageable = PageRequest.of(page, 9);

        List<CategorySortingDto> categorySortingDtos = categoryRepository.getSortingCategories();

        Page<CategorySortingDto> categories = getCategorySortingDtos(pageable, categorySortingDtos);

        return categories;

    }

    @Override
    public List<CategorySortingDto> getRecommendCategories() {

        List<Category> categories = categoryRepository.findAll().subList(0, 6);

        List<CategorySortingDto> dtos = new ArrayList<>();

        categories.stream().forEach(category -> {
            CategorySortingDto dto = CategorySortingDto.builder()
                    .id(category.getId())
                    .tagName(category.getTagName()).build();

            Optional<Bookmark> result = bookmarkRepository.findBookmarkByUserIdAndCategoryId(0, dto.getId());

            if (result.isPresent()) {
                dto.setBookmark(true);
            } else {
                dto.setBookmark(false);
            }

            dtos.add(dto);

        });

        return dtos;
    }

    private Page<CategorySortingDto> getCategorySortingDtos(Pageable pageable, List<CategorySortingDto> categorySortingDtos) {
        categorySortingDtos.stream().forEach(dto -> {
            Optional<Bookmark> result = bookmarkRepository.findBookmarkByUserIdAndCategoryId(0, dto.getId());

            if (result.isPresent()) {
                dto.setBookmark(true);
            } else {
                dto.setBookmark(false);
            }
        });

        categorySortingDtos = categorySortingDtos.stream().sorted(Comparator.comparing(CategorySortingDto::getBookmarkCnt).reversed()).collect(Collectors.toList());

        int start= (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > categorySortingDtos.size() ? categorySortingDtos.size() : (start + pageable.getPageSize());

        Page<CategorySortingDto> categories = new PageImpl<>(categorySortingDtos.subList(start, end), pageable, categorySortingDtos.size());
        return categories;
    }
}
