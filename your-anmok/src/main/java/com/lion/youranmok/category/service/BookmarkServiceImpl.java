package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.BookmarkDto;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;


    @Override
    public void register(int categoryId) {

        BookmarkDto bookmarkDto = BookmarkDto.builder().categoryId(categoryId).build();

        Bookmark bookmark = dtoToEntity(bookmarkDto);

        bookmarkRepository.save(bookmark);
    }
}
