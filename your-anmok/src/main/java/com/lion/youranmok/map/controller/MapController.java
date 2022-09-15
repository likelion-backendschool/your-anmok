package com.lion.youranmok.map.controller;

import com.lion.youranmok.map.dto.MapDto;
import com.lion.youranmok.map.service.MapService;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.service.AddPlaceService;
import com.lion.youranmok.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/map")
@Controller
@RequiredArgsConstructor
public class MapController {
    private final MapService mapService;

    @GetMapping("/addPlace/{place_name}/{address_name}")
    public String addPlace(@PathVariable String place_name, @PathVariable String address_name){

        mapService.add(place_name,address_name);

        return "redirect:/";

    }

//    @GetMapping("/mapSearch")
//    public String searchMap(){
//
//    }

    @GetMapping("/searchMap")
    public String searchPlace(@RequestParam("placeKeyword") String placeKeyword, Model model){

        model.addAttribute("placeKeyword",placeKeyword);
        return "map/searchMap";
    }

    @GetMapping("/category")
    public String categoryDisplay(@RequestParam("id") int id, Model model){

        List<MapDto> categoryPlaceList = mapService.getCateogryPlaceList(id);
        for (MapDto mapDto : categoryPlaceList){
            System.out.println(mapDto.getName());
        }
        model.addAttribute("categoryPlaceList",categoryPlaceList);

        return "map/categoryMap";
    }

    private final AddPlaceService addPlaceService;
    private final PlaceService placeService;

    @PostMapping("/addPlace")
    public String addPlace(String placeName, String address, Integer rating, MultipartFile placeImg) {
        Place place = placeService.getPlaceByNameAndAddress(placeName, address);

        Integer starAvg = placeService.getStarAvg(place.getId());
        placeService.setStar(place, starAvg);
        addPlaceService.upload(place.getId(), rating, placeImg);

        return "redirect:/";
    }

}
