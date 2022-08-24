package com.lion.youranmok.category.repository;

import com.lion.youranmok.category.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    Optional<Bookmark> findBookmarkByUserIdAndCategoryId(int userId, int categoryId);

    void deleteBookmarkByUserIdAndCategoryId(int userId, int categoryId);

}
