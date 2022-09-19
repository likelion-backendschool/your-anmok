package com.lion.youranmok.place.service;

import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.entity.PlaceImage;
import com.lion.youranmok.place.entity.PlaceReview;
import com.lion.youranmok.place.repository.PlaceImageRepository;
import com.lion.youranmok.place.repository.PlaceReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceReviewService {
    private final FileHandler fileHandler;
    private final PlaceImageRepository placeImageRepository;
    private final PlaceReviewRepository placeReviewRepository;

    @Transactional
    public void upload(Place place, Integer star, List<MultipartFile> files) throws Exception {
        PlaceReview placeReview = new PlaceReview(
                star,
                place
        );

        List<PlaceImage> placeImageList = fileHandler.parseFileInfo(placeReview, files);

        // 파일이 존재할 때에만 처리
        if(!placeImageList.isEmpty()) {
            for(PlaceImage placeImage : placeImageList) {
                // 파일을 DB에 저장
                placeReview.addPhoto(placeImageRepository.save(placeImage));
            }
        }
        else{
            saveReview(place, star);
        }
    }

    public void saveReview(Place place, Integer star){
        PlaceReview placeReview = new PlaceReview();
        placeReview.setPlace(place);
        placeReview.setStar(star);

        placeReviewRepository.save(placeReview);
    }

}