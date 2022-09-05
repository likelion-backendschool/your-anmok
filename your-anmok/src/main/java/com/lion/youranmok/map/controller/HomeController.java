package com.lion.youranmok.map.controller;

import com.lion.youranmok.map.dto.MapDto;
import com.lion.youranmok.map.entity.Map;
import com.lion.youranmok.map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MapService mapService;

    @GetMapping("/")
    public String home(){
        List<MapDto> placeList = mapService.getAllPlaceList();
        for (MapDto mapDto : placeList){
            System.out.println(mapDto.getName());
        }
        return "map/homeMap";
    }

    @GetMapping("/searchMap")
    public String searchMap(){
        return "map/searchMap";
    }
}