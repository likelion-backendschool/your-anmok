package com.lion.youranmok.place.service;

import com.lion.youranmok.place.entity.PlaceCategoryMap;
import com.lion.youranmok.place.repository.PlaceCategoryMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceCategoryMapService {
    private final PlaceCategoryMapRepository placeCategoryMapRepository;

    public void save(Integer placeId, Integer categoryId) {
        PlaceCategoryMap placeCategoryMap = new PlaceCategoryMap();
        placeCategoryMap.setPlaceId(placeId);
        placeCategoryMap.setCategoryId(categoryId);
        placeCategoryMapRepository.save(placeCategoryMap);
    }

    public List<Integer> getDistinctCategoryIdByPlaceId(Integer id) {
        return placeCategoryMapRepository.getDistinctCategoryIdByPlaceId(id);
    }

    public List<Integer> getPlaceIdByCategoryId(Integer id){
        return placeCategoryMapRepository.getPlaceByCategoryId(id);
    }
}
