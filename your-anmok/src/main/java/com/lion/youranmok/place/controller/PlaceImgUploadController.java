package com.lion.youranmok.place.controller;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.entity.PlaceImage;
import com.lion.youranmok.place.service.PlaceImgUploadService;
import com.lion.youranmok.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class PlaceImgUploadController {

    private final PlaceImgUploadService placeImgUploadService;
    private final PlaceService placeService;

    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("img1") MultipartFile img1, @RequestParam("img2") MultipartFile img2) {
        try {
            img1.transferTo(new File(genFileDirPath + "/1.png"));
            img2.transferTo(new File(genFileDirPath + "/2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "업로드 완료!";
    }

    @GetMapping("/addPlace")
    public String showJoin(){
        return "popup/addPlace";
    }

    @PostMapping("/addPlace")
    @ResponseBody
    public String addPlaceImg(String placeName, String address, MultipartFile placeImg) {
        Place place = placeService.getPlaceIdByName(placeName, address);

        placeImgUploadService.upload(place.getId(), placeImg);

        return "가입완료";
    }
}
