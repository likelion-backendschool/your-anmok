package com.lion.youranmok.place.service;

import com.lion.youranmok.place.entity.PlaceImage;
import com.lion.youranmok.place.repository.PlaceImgUploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceImgUploadService {
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    private final PlaceImgUploadRepository placeImgUploadRepository;

    public PlaceImage upload(Integer placeId, MultipartFile placeImg) {
        String placeImgRelPath = "placeImg/" + UUID.randomUUID().toString() + ".png";
        File placeImgFile = new File(genFileDirPath + "/" + placeImgRelPath);

        placeImgFile.mkdirs(); // 관련된 폴더가 혹시나 없다면 만들어준다.

        try {
            placeImg.transferTo(placeImgFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PlaceImage placeImage = PlaceImage.builder()
                .placeId(placeId)
                .placeImg(placeImgRelPath)
                .build();

        placeImgUploadRepository.save(placeImage);

        return placeImage;

    }
}
