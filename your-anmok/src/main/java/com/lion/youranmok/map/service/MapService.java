package com.lion.youranmok.map.service;

import com.lion.youranmok.map.dto.MapDto;
import com.lion.youranmok.map.entity.Map;
import com.lion.youranmok.map.repository.MapRepository;
import com.lion.youranmok.place.dto.PlaceDto;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapService {
    private final MapRepository mapRepository;

    private final PlaceRepository placeRepository;

//    public Place dtoToEntity(PlaceDto placeDto){
//        Place place = Place.builder()
//                .id(placeDto.getId())
//                .name(placeDto.getName())
//                .address(placeDto.getAddress())
//                .build();
//
//        return map;
//    }

//    public MapDto entityToDto(Map map){
//        MapDto mapDto = MapDto.builder()
//                .id(map.getId())
//                .name(map.getName())
//                .address(map.getAddress())
//                .build();
//
//        return mapDto;
//    }

//    public void add(String name, String address){
//        Map map = new Map();
//
//        map.setName(name);
//        map.setAddress(address);
//
//        mapRepository.save(map);
//    }

    public List<Place> getAllPlaceList() {
        List<MapDto> mapDtoList = new ArrayList<>();

        List<Place> mapList = placeRepository.findAll();
//        mapDtoList = mapList.stream().map(e -> {MapDto mapDto = entityToDto(e); return mapDto;}).collect(Collectors.toList());

        return mapList;
    }

    public List<MapDto> getCateogryPlaceList(int id) {
        List<MapDto> mapDtoList = new ArrayList<>();

        List<Map> mapList = mapRepository.findByCategoryId(id);
//        mapDtoList = mapList.stream().map(e->{MapDto mapDto = entityToDto(e); return mapDto;}).collect(Collectors.toList());


        return mapDtoList;

    }
}
