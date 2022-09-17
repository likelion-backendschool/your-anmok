package com.lion.youranmok.place.controller;

import com.lion.youranmok.category.dto.CategoryDto;
import com.lion.youranmok.category.entity.Category;
import com.lion.youranmok.category.service.CategoryService;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.service.GatheringService;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.entity.PlaceImage;
import com.lion.youranmok.place.service.PlaceCategoryMapService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceImageService placeImageService;
    private final PlaceReviewService placeReviewService;
    private final CategoryService categoryService;
    private final GatheringService gatheringService;
    private final PlaceCategoryMapService placeCategoryMapService;

    @RequestMapping("/place/{id}")
    public String placeDetail(Model model, @PathVariable(value="id")Integer id){
        Place place = this.placeService.getPlace(id);
        List<GatheringBoard> placeGatheringDtos = gatheringService.getGatheringListByPlaceId(id);
        List<PlaceImage> placeImages = placeImageService.getPlaceImagesByPlaceId(id);

        System.out.println(placeImages.size());

        model.addAttribute("place", place);
        model.addAttribute("stars", place.getStar());
        model.addAttribute("emptystars", 5-place.getStar());
        model.addAttribute("gatheringList", placeGatheringDtos);
        model.addAttribute("placeImageList", placeImages);

        return "map/homeMap";
    }

    @PostMapping("/addPlace")
    public String addPlace(HttpServletRequest request, String placeName, String address, Double lat, Double lon, String categorySelect, Integer rating, @RequestParam(value = "placeImg") List<MultipartFile> placeImgs) throws Exception {
        Place place = placeService.getPlaceByNameAndAddress(placeName, address);

        Category category = Category.builder()
                .tagName(categorySelect)
                .build();

        //카테고리 등록
        Integer categoryid = categoryService.addCategory(categoryService.entityToDto(category));
        //이미 등록된 카테고리의 id 가져오기
        if(categoryid==0){
            categoryid = categoryService.getCategoryByTagName(categorySelect).getId();
        }

        if (place==null){
            placeService.savePlace(placeName, address, lat, lon, rating, placeImgs);
            place = placeService.getPlaceByNameAndAddress(placeName, address);
        }

        placeCategoryMapService.save(place.getId(), categoryid);
        placeReviewService.upload(place, rating, placeImgs);

        Integer starAvg = placeService.getStarAvg(place.getId());
        placeService.setStar(place, starAvg);


        return "redirect:" + request.getHeader("Referer");
    }

}
