package com.lion.youranmok.place.controller;


import com.lion.youranmok.gathering.dto.GatheringPreviewDto;
import com.lion.youranmok.place.dto.PlaceGatheringDto;
import com.lion.youranmok.place.dto.PlaceTagDto;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.service.AddPlaceService;
import com.lion.youranmok.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @RequestMapping("/place/{id}")
    public String placeDetail(Model model, @PathVariable(value="id")Integer id){
        Place place = this.placeService.getPlace(id);
        List<PlaceTagDto> placeTagDtos = this.placeService.getTagName(id);
        List<PlaceGatheringDto> placeGatheringDtos = placeService.getGatheringListByPlaceId(id);

        model.addAttribute("place", place);
        model.addAttribute("tagNameList", placeTagDtos);
        model.addAttribute("stars", place.getStar());
        model.addAttribute("emptystars", 5-place.getStar());
        model.addAttribute("gatheringList", placeGatheringDtos);

        return "map/homeMap";
    }

    private final AddPlaceService addPlaceService;

    @PostMapping("/addPlace")
    public String addPlace(String placeName, String address, Integer rating, MultipartFile placeImg) {
        Place place = placeService.getPlaceByNameAndAddress(placeName, address);

        Integer starAvg = placeService.getStarAvg(place.getId());
        placeService.setStar(place, starAvg);
        addPlaceService.upload(place.getId(), rating, placeImg);

        return "redirect:/";
    }

}
