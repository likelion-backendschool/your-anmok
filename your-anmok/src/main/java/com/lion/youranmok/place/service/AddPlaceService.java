package com.lion.youranmok.place.service;

import com.lion.youranmok.place.entity.PlaceReview;
import com.lion.youranmok.place.repository.AddPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddPlaceService {
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    private final AddPlaceRepository addPlaceRepository;

    public PlaceReview upload(Integer placeId, Integer star, MultipartFile placeImg) {
        String placeImgRelPath = "placeImg/" + UUID.randomUUID().toString() + ".png";
        File placeImgFile = new File(genFileDirPath + "/" + placeImgRelPath);

        placeImgFile.mkdirs(); // 관련된 폴더가 혹시나 없다면 만들어준다.

        try {
            placeImg.transferTo(placeImgFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PlaceReview placeReview = PlaceReview.builder()
                .placeId(placeId)
                .star(star)
                .placeImg(placeImgRelPath)
                .build();

        addPlaceRepository.save(placeReview);

        return placeReview;

    }
}