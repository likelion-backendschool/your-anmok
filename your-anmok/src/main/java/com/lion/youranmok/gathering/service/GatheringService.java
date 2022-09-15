package com.lion.youranmok.gathering.service;

import com.lion.youranmok.gathering.dto.*;
import com.lion.youranmok.gathering.repository.GatheringCommentRepository;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import com.lion.youranmok.place.dto.PlaceGatheringDto;
import com.lion.youranmok.place.entity.Place;
import com.lion.youranmok.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GatheringService {
    private final GatheringRepository gatheringRepository;
    private final GatheringCommentRepository gatheringCommentRepository;

    private final PlaceRepository placeRepository;

    public GatheringBoard create(CreateForm createForm) {
        GatheringBoard gatheringBoard = new GatheringBoard();
        Place place = placeRepository.findById(createForm.getPlaceId()).orElse(null);

        gatheringBoard.setTotalCnt(createForm.getTotalCnt());
        gatheringBoard.setGatherCnt(createForm.getGatherCnt());
        gatheringBoard.setText(createForm.getContent());
        gatheringBoard.setTitle(createForm.getTitle());
        gatheringBoard.setCreatedAt(LocalDateTime.now());
        gatheringBoard.setPlace(place);
        gatheringBoard.setModifiedAt(LocalDateTime.now());
        gatheringBoard.setIsExpired(false);
        gatheringBoard.setDate( LocalDate.ofInstant(createForm.getDate().toInstant(), ZoneId.systemDefault()));

        // TODO : 로그인 후 고유한 유저를 받아와서 저장해야함 지금은 랜덤유저임..
        Random random = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
        random.setSeed(System.currentTimeMillis()); //시드값 설정을 따로 할수도 있음
        gatheringBoard.setUserId(random.nextInt(7)+1);

        return gatheringRepository.save(gatheringBoard);
    }

    public void modify(int id, CreateForm createForm) {
        GatheringBoard gatheringBoard = gatheringRepository.findById(id).orElse(null);
        Place place = placeRepository.getReferenceById(createForm.getPlaceId());

        if(gatheringBoard == null) {
            throw new NoResultException();
        }


        gatheringBoard.setTotalCnt(createForm.getTotalCnt());
        gatheringBoard.setGatherCnt(createForm.getGatherCnt());
        gatheringBoard.setText(createForm.getContent());
        gatheringBoard.setTitle(createForm.getTitle());
        gatheringBoard.setPlace(place);
        gatheringBoard.setModifiedAt(LocalDateTime.now());
        gatheringBoard.setDate(LocalDate.ofInstant(createForm.getDate().toInstant(), ZoneId.systemDefault()));

        gatheringRepository.save(gatheringBoard);
    }


    public List<GatheringListDetailDto> listByCriteria() {
        return gatheringRepository.listByCriteria();
    }

    public GatheringDetailDto getDetailById(int id) {
        return gatheringRepository.getDetailById(id);
    }

    /**
     * 카테고리 페이지의 댓글 순으로 나열하기 위한 DTO 가져오는 메서드
     */
    public List<GatheringPreviewDto> getPreview() {

        List<GatheringPreviewDto> previewDtos = gatheringRepository.getPreview();
        previewDtos.forEach(preview -> {

            int cnt = gatheringCommentRepository.getCommentCnt(preview.getBoardId());
            preview.setCommentCnt(cnt);

        });

        previewDtos = previewDtos.stream().sorted(Comparator.comparing(GatheringPreviewDto::getCommentCnt).reversed()).collect(Collectors.toList());
        previewDtos = previewDtos.subList(0, 7);

        return previewDtos;
    }

    public GatheringBoard findById(int id) {
        return gatheringRepository.findById(id).orElse(null);
    }

    public List<CreateSearchDto> findCreateSearchResultByKeyword(String searchKeyword) {
        return gatheringRepository.findCreateSearchResultByKeyword(searchKeyword);
    }

    public void delete(int id) {
        GatheringBoard gatheringBoard = gatheringRepository.findById(id).orElse(null);
        gatheringRepository.delete(gatheringBoard);
    }


    public List<GatheringBoard> getGatheringListByPlaceId(Integer id) {
        return gatheringRepository.getAllByPlaceId(id);
    }
}
