package com.lion.youranmok.place.controller;


import com.lion.youranmok.place.dto.PlaceGatheringDto;
import com.lion.youranmok.place.dto.PlaceTagDto;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.entity.PlaceImage;
import com.lion.youranmok.place.service.PlaceImageService;
import com.lion.youranmok.place.service.PlaceReviewService;
import com.lion.youranmok.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceImageService placeImageService;

    @RequestMapping("/place/{id}")
    public String placeDetail(Model model, @PathVariable(value="id")Integer id){
        Place place = this.placeService.getPlace(id);
        List<PlaceTagDto> placeTagDtos = this.placeService.getTagName(id);
        List<PlaceGatheringDto> placeGatheringDtos = placeService.getGatheringListByPlaceId(id);
        List<PlaceImage> placeImages = placeImageService.getPlaceImagesByPlaceId(id);

        System.out.println(placeImages.size());

        model.addAttribute("place", place);
        model.addAttribute("tagNameList", placeTagDtos);
        model.addAttribute("stars", place.getStar());
        model.addAttribute("emptystars", 5-place.getStar());
        model.addAttribute("gatheringList", placeGatheringDtos);
        model.addAttribute("placeImageList", placeImages);

        return "map/homeMap";
    }

    private final PlaceReviewService placeReviewService;

    @PostMapping("/addPlace")
    public String addPlace(String placeName, String address, Double lat, Double lon, Integer rating, @RequestParam(value = "placeImg") List<MultipartFile> placeImgs) throws Exception {
        Place place = placeService.getPlaceByNameAndAddress(placeName, address);

        if (place==null){
            placeService.savePlace(placeName, address, lat, lon, rating, placeImgs);
            return "map/homeMap";
        }
        placeReviewService.upload(place, rating, placeImgs);

        Integer starAvg = placeService.getStarAvg(place.getId());
        placeService.setStar(place, starAvg);

        return "map/homeMap";
    }

}
