package com.lion.youranmok.map.controller;

import com.lion.youranmok.map.dto.MapDto;
import com.lion.youranmok.map.entity.Map;
import com.lion.youranmok.map.service.MapService;
import com.lion.youranmok.place.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MapService mapService;

//    @RequestMapping(value = "/", method = {RequestMethod.GET})
//    public String home(Model model){
////        ModelAndView mv = new ModelAndView();
//
//        List<Place> placeList = mapService.getAllPlaceList();
////        for (MapDto mapDto : placeList){
////            System.out.println(mapDto.getName());
////        }
//        model.addAttribute("allPlaceList",placeList);
////        mv.addObject("placeInfos","placeList");
////        mv.setViewName("map/homeMap");
//
////        model.addAttribute("message", "정상적으로 처리되었습니다.");
////        model.addAttribute("searchUrl", "https://www.google.com");
//
//        return "map/homeMap";
//    }

//    @GetMapping("/searchMap")
//    public String searchMap(){
//        return "map/searchMap";
//    }
}