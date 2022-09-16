package com.lion.youranmok.place.service;

import com.lion.youranmok.place.entity.PlaceImage;
import com.lion.youranmok.place.repository.PlaceImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceImageService {
    private final PlaceImageRepository placeImageRepository;

    public List<PlaceImage> getPlaceImagesByPlaceId(Integer id){
        return placeImageRepository.getPlaceImagesByPlaceId(id);
    }
}
