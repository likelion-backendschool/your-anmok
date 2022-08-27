package com.lion.youranmok.map.service;

import com.lion.youranmok.map.entity.Map;
import com.lion.youranmok.map.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapService {
    private final MapRepository mapRepository;

    public void add(String name, String address){
        Map map = new Map();

        map.setName(name);
        map.setAddress(address);

        mapRepository.save(map);
    }
}
