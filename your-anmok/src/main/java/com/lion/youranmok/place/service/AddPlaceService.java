package com.lion.youranmok.place.service;

import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.entity.PlaceImage;
import com.lion.youranmok.place.entity.PlaceReview;
import com.lion.youranmok.place.repository.AddPlaceRepository;
import com.lion.youranmok.place.repository.PlaceImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddPlaceService {
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    private final AddPlaceRepository addPlaceRepository;
    private final FileHandler fileHandler;
    private final PlaceImageRepository placeImageRepository;

//    public PlaceReview upload(Place place, Integer star, MultipartFile placeImg) {
//        String placeImgRelPath = "placeImg/" + UUID.randomUUID().toString() + ".png";
//        File placeImgFile = new File(genFileDirPath + "/" + placeImgRelPath);
//
//        placeImgFile.mkdirs(); // 관련된 폴더가 혹시나 없다면 만들어준다.
//
//        try {
//            placeImg.transferTo(placeImgFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        PlaceReview placeReview = PlaceReview.builder()
//                .star(star)
//                .placeImg(placeImgRelPath)
//                .place(place)
//                .build();
//
//        addPlaceRepository.save(placeReview);
//
//        return placeReview;
//
//    }

    @Transactional
    public PlaceReview upload(Place place, Integer star, List<MultipartFile> files) throws Exception {
        PlaceReview placeReview = new PlaceReview(
                star,
                place
        );

        List<PlaceImage> placeImageList = fileHandler.parseFileInfo(files);

        // 파일이 존재할 때에만 처리
        if(!placeImageList.isEmpty()) {
            for(PlaceImage placeImage : placeImageList) {
                // 파일을 DB에 저장
                placeReview.addPhoto(placeImageRepository.save(placeImage));
            }
        }

        return placeReview;
    }
}