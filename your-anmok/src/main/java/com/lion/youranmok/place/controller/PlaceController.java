package com.lion.youranmok.place.controller;

import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @RequestMapping("/place/{id}")
    public String placeDetail(Model model, @PathVariable("id")Integer id){
        Place place = this.placeService.getPlace(id);
        model.addAttribute("place", place);
        return "map/homeMap";
    }

}
