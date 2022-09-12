package com.lion.youranmok.place.service;

import com.lion.youranmok.DataNotFoundException;
import com.lion.youranmok.place.dto.PlaceGatheringDto;
import com.lion.youranmok.place.dto.PlaceTagDto;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.entity.PlaceReview;
import com.lion.youranmok.place.repository.AddPlaceRepository;
import com.lion.youranmok.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final AddPlaceRepository addPlaceRepository;

    public Place getPlace(Integer id){
        Optional<Place> place = this.placeRepository.findById(id);
        if(place.isPresent()){
            return place.get();
        }
        else{
            throw new DataNotFoundException("question not found");
        }
    }

    public Place getPlaceByNameAndAddress(String placename, String address){
        System.out.println(placename);
        System.out.println(address);
        Optional<Place> place = this.placeRepository.findPlaceByNameAndAddress(placename, address);
        if(place.isPresent()){
            return place.get();
        }
        else{
            throw new DataNotFoundException("place not found");
        }
    }

    public List<PlaceTagDto> getTagName(Integer id){
        return placeRepository.getTagNameById(id);
    }

    public List<PlaceGatheringDto> getGatheringListByPlaceId(Integer id) {
        return placeRepository.getGatheringListByPlaceId(id);
    }

    public Integer getStarAvg(Integer id) {
        List<PlaceReview> placeReviews = addPlaceRepository.getAllByPlaceId(id);
        int starSum = 0;
        int reviewCnt = placeReviews.size();

        if (reviewCnt == 0) {
            return 0;
        } else {
            for (int i = 0; i < reviewCnt; i++) {
                PlaceReview placeReview = placeReviews.get(i);
                starSum += placeReview.getStar();
            }
        }
        return Math.round(starSum / reviewCnt);
    }

    public void setStar(Place place, Integer starAvg) {
        place.setStar(starAvg);
        placeRepository.save(place);
    }
}
