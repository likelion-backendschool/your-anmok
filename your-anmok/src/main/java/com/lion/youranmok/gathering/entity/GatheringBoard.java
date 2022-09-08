package com.lion.youranmok.gathering.entity;

import com.lion.youranmok.place.entity.Place;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import javax.xml.stream.events.Comment;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class GatheringBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne
    private Place place;

    @Column
    private Integer userId;

    @Column(length = 50)
    private String title;

    @Column(length = 500)
    private String text;

    @Column
    private LocalDate date;

    @Column(length = 1)
    private Boolean isExpired;

    @Column
    private Integer totalCnt;

    @Column
    private Integer gatherCnt;

    @OneToMany(mappedBy = "board", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private Set<GatheringComment> commentList;

}
