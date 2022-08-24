package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Bookmark;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.repository.BookmarkRepository;
import com.lion.youranmok.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        List<CategoryDto> categories = new ArrayList<>();

        categories = categoryRepository.findAll().stream().map(i -> {
            CategoryDto dto = entityToDto(i);
            return dto;
        }).collect(Collectors.toList());


        return categories;
    }

    // TODO 북마크 체크하는 함수 추출 리팩토링 필요

    @Override
    public Page<CategoryDto> findByTagNameContaining(int page, String keyword) {

        Pageable pageable = PageRequest.of(page, 9);

        Page<Category> categoryPage = categoryRepository.findByTagNameContaining(pageable, keyword);

        Page<CategoryDto> categories = categoryPage.map(i -> {
            CategoryDto dto = entityToDto(i);

            Optional<Bookmark> result = bookmarkRepository.findBookmarkByUserIdAndCategoryId(0, dto.getId());

            if (result.isPresent()) {
                dto.setBookmark(true);
            } else {
                dto.setBookmark(false);
            }

            return dto;
        });

        return categories;
    }

    @Override
    public Page<CategoryDto> getListByPaging(int page) {

        Pageable pageable = PageRequest.of(page, 9);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        Page<CategoryDto> categories = categoryPage.map(i -> {
            CategoryDto dto = entityToDto(i);

            Optional<Bookmark> result = bookmarkRepository.findBookmarkByUserIdAndCategoryId(0, dto.getId());

            if (result.isPresent()) {
                dto.setBookmark(true);
            } else {
                dto.setBookmark(false);
            }

            return dto;
        });


        return categories;
    }
}
