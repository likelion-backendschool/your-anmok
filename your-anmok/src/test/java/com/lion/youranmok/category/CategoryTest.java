package com.lion.youranmok.category;

import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    /*
    DB에 테스트 값 넣기 위한 용도
     */
    @Test
    public void makeTestData() {

        Category data1 = Category.builder()
                .tagName("공부하기 좋은 카페")
                .build();

        Category data2 = Category.builder()
                .tagName("분위기 좋은 카페")
                .build();

        Category data3 = Category.builder()
                .tagName("커피가 맛있는 카페")
                .build();

        Category data4 = Category.builder()
                .tagName("걷기 좋은 거리")
                .build();

        Category data5 = Category.builder()
                .tagName("데이트하기 좋은 장소")
                .build();

        Category data6 = Category.builder()
                .tagName("산책하기 좋은 장소")
                .build();

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(data1);
        categoryList.add(data2);
        categoryList.add(data3);
        categoryList.add(data4);
        categoryList.add(data5);
        categoryList.add(data6);

        categoryRepository.saveAll(categoryList);


    }


}
