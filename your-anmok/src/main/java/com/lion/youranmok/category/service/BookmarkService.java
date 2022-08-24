package com.lion.youranmok.category.service;

import com.lion.youranmok.category.dto.BookmarkDto;
import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Bookmark;
import com.lion.youranmok.category.entity.Category;

import java.awt.print.Book;

public interface BookmarkService {

    void register(int categoryId);

    default Bookmark dtoToEntity(BookmarkDto dto) {

        Bookmark entity = Bookmark.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .categoryId(dto.getCategoryId())
                .build();

        return entity;

    }

    default BookmarkDto entityToDto(BookmarkDto entity) {

        BookmarkDto dto = BookmarkDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .categoryId(entity.getCategoryId())
                .build();

        return dto;

    }

}
