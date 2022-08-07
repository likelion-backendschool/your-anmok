package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    public List<CategoryDto> findAll() {

        List<CategoryDto> categories = new ArrayList<>();

        categories = categoryRepository.findAll().stream().map(i -> {
            CategoryDto dto = entityToDto(i);
            return dto;
        }).collect(Collectors.toList());


        return categories;
    }

    @Override
    public List<CategoryDto> findByTitleContaining(String keyword) {

        List<CategoryDto> categories = new ArrayList<>();

        categories = categoryRepository.findByTitleContaining(keyword).stream().map(i -> {
            CategoryDto dto = entityToDto(i);
            return dto;
        }).collect(Collectors.toList());

        return categories;
    }
}
