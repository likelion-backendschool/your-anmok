package com.lion.youranmok.map.controller;

import com.lion.youranmok.map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
