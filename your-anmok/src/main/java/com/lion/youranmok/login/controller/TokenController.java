package com.lion.youranmok.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lion.youranmok.login.entity.BaseTimeEntity;
import com.lion.youranmok.login.entity.Kakao_User;
import com.lion.youranmok.login.model.*;
import com.lion.youranmok.login.repository.KakaoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/")
public class TokenController {
    @Autowired
    private KakaoUserRepository kakaoUserRepository;

    @Value("${Kakao_Client}")
    private String Kakao_Client;
    @Value("${Kakao_Callback}")
    private String Kakao_Callback;

    @GetMapping("/login")
    public String getList() {
        Map<String, String> kakao_key = new HashMap<String,String>();
        kakao_key.put("key1",Kakao_Client);
        kakao_key.put("key2",Kakao_Callback);
        return "LoginForm";
        }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) throws JsonProcessingException {
        BaseTimeEntity baseTimeEntity = new BaseTimeEntity();
        //Post 방식으로 key=value 데이터를 요청(카카오쪽으로)
        RestTemplate rt = new RestTemplate();
        //httpHeaders 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //httpBody 오브젝트 생성
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",Kakao_Client);
        params.add("redirect_uri",Kakao_Callback);
        params.add("code",code);

        //httpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
            new HttpEntity<>(params,headers);

        //Http 요청하기 - Post방식으로 - 그리고 Response변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        //Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        oauthToken = objectMapper.readValue(response.getBody(),OauthToken.class);

        //System.out.println("Kakao Access Token :" + oauthToken.getAccess_token());
        //Post 방식으로 key=value 데이터를 요청(카카오쪽으로)
        RestTemplate rt2 = new RestTemplate();

        //httpHeaders 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //httpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);

        //Http 요청하기 - Post방식으로 - 그리고 Response변수의 응답 받음.
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );
        
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try{
            kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        String data = kakaoProfile.getKakao_account().getEmail();
        Kakao_User kakao_user = kakaoUserRepository.findByEmail(data);

        if(kakao_user != null){
            return "map/homeMap";
        }
        else{
            kakaoUserRepository.save(Kakao_User.builder()
                    .nickname(kakaoProfile.getKakao_account().getProfile().getNickname())
                    .email(kakaoProfile.getKakao_account().getEmail())
                    .profile_picture(kakaoProfile.getKakao_account().getProfile().getProfile_image_url())
                    .created_at(LocalDateTime.now())
                    .build());
            return "map/homeMap";
        }
    }
}