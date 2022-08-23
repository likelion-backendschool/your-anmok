package com.lion.youranmok.place.service;

import com.lion.youranmok.DataNotFoundException;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public Place getPlace(Integer id){
        Optional<Place> place = this.placeRepository.findById(id);
        if(place.isPresent()){
            return place.get();
        }
        else{
            throw new DataNotFoundException("question not found");
        }
    }
}
