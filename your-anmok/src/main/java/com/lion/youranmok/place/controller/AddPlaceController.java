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
    public String addPlace(){
        return "popup/addPlace";
    }

    @PostMapping("/addPlace")
    public String addPlace(String placeName, String address, Integer rating, MultipartFile placeImg) {
        Place place = placeService.getPlaceByNameAndAddress(placeName, address);

        Integer starAvg = placeService.getStarAvg(place.getId());
        placeService.setStar(place, starAvg);
        addPlaceService.upload(place.getId(), rating, placeImg);

        return "redirect:/";
    }
}
