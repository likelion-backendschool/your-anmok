package com.lion.youranmok.map.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.service.CategoryService;
//import com.lion.youranmok.map.dto.MapDto;
//import com.lion.youranmok.map.service.MapService;
import com.lion.youranmok.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/map")
@Controller
@RequiredArgsConstructor
public class MapController {
//    private final MapService mapService;
    private final CategoryService categoryService;

//    @GetMapping("/addPlace/{place_name}/{address_name}")
//    public String addPlace(@PathVariable String place_name, @PathVariable String address_name){
//
//        mapService.add(place_name,address_name);
//
//        return "redirect:/";
//
//    }

//    @GetMapping("/mapSearch")
//    public String searchMap(){
//
//    }

//    @GetMapping("/searchMap")
//    public String searchPlace(@AuthenticationPrincipal MemberContext member, @RequestParam("placeKeyword") String placeKeyword, Model model){
//        List<CategoryDto> categoryList = categoryService.findAll();
//        model.addAttribute("placeKeyword",placeKeyword);
//        model.addAttribute("categoryList",categoryList);
//        return "map/searchMap";
//    }

//    @GetMapping("/category")
//    public String categoryDisplay(@RequestParam("id") int id, Model model){
//
//        List<MapDto> categoryPlaceList = mapService.getCateogryPlaceList(id);
//        for (MapDto mapDto : categoryPlaceList){
//            System.out.println(mapDto.getName());
//        }
//        model.addAttribute("categoryPlaceList",categoryPlaceList);
//
//        return "map/categoryMap";
//    }
    // localhost:8080/category?id=14
}

