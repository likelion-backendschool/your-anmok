package com.lion.youranmok.place.controller;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.service.AddPlaceService;
import com.lion.youranmok.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class AddPlaceController {

    private final AddPlaceService addPlaceService;
    private final PlaceService placeService;

    @GetMapping("/addPlace")
    public String addPlaceImg(){
        return "popup/addPlace";
    }

    @PostMapping("/addPlace")
    public String addPlaceImg(String placeName, String address, MultipartFile placeImg) {
        Place place = placeService.getPlaceIdByName(placeName, address);

        addPlaceService.upload(place.getId(), placeImg);

        return "redirect:/";
    }
}
