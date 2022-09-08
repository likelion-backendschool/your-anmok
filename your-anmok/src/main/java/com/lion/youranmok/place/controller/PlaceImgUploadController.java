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

    @GetMapping("/addPlace")
    public String addPlaceImg(){
        return "popup/addPlace";
    }

    @PostMapping("/addPlace")
    public String addPlaceImg(String placeName, String address, MultipartFile placeImg) {
        Place place = placeService.getPlaceIdByName(placeName, address);

        placeImgUploadService.upload(place.getId(), placeImg);

        return "redirect:/";
    }
}
