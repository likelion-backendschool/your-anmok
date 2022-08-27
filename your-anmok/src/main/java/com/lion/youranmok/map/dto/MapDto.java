package com.lion.youranmok.map.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
public class MapDto {

    private int id;

    private String name;

    private String address;

    public MapDto(int id, String name, String address){
        this.id=id;
        this.name=name;
        this.address=address;
    }
}
