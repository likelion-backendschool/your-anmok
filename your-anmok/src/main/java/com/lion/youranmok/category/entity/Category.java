package com.lion.youranmok.category.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Integer placeId;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

}
