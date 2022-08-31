package com.lion.youranmok.map.service;

import com.lion.youranmok.map.dto.MapDto;
import com.lion.youranmok.map.entity.Map;
import com.lion.youranmok.map.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapService {
    private final MapRepository mapRepository;

    public Map dtoToEntity(MapDto mapDto){
        Map map = Map.builder()
                .id(mapDto.getId())
                .name(mapDto.getName())
                .address(mapDto.getAddress())
                .build();

        return map;
    }

    public MapDto entityToDto(Map map){
        MapDto mapDto = MapDto.builder()
                .id(map.getId())
                .name(map.getName())
                .address(map.getAddress())
                .build();

        return mapDto;
    }

    public void add(String name, String address){
        Map map = new Map();

        map.setName(name);
        map.setAddress(address);

        mapRepository.save(map);
    }

    public List<MapDto> getAllPlaceList() {
        List<MapDto> mapDtoList = new ArrayList<>();

        List<Map> mapList = mapRepository.findAll();
        mapDtoList = mapList.stream().map(e -> {MapDto mapDto = entityToDto(e); return mapDto;}).collect(Collectors.toList());

        return mapDtoList;
    }

}
