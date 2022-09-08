package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateForm {
    private String title;
    private int gatherCnt;
    private int totalCnt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer placeId;
    private String placeName;
    private String content;
}
