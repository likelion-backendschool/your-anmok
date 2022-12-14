package com.lion.youranmok.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lion.youranmok.login.dto.KakaoUserDto;
import com.lion.youranmok.login.entity.BaseTimeEntity;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.login.model.*;
import com.lion.youranmok.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TokenController {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;


    @Value("${Kakao_Client}")
    private String Kakao_Client;
    @Value("${Kakao_Callback}")
    private String Kakao_Callback;

    @GetMapping("/auth/login")
    public String getList() {
        Map<String, String> kakao_key = new HashMap<String,String>();
        kakao_key.put("key1",Kakao_Client);
        kakao_key.put("key2",Kakao_Callback);
        return "login/LoginForm";
        }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) throws JsonProcessingException {
        BaseTimeEntity baseTimeEntity = new BaseTimeEntity();
        //Post ???????????? key=value ???????????? ??????(??????????????????)
        RestTemplate rt = new RestTemplate();
        //httpHeaders ???????????? ??????
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //httpBody ???????????? ??????
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id",Kakao_Client);
        params.add("redirect_uri",Kakao_Callback);
        params.add("code",code);

        //httpHeader??? HttpBody??? ????????? ??????????????? ??????
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
            new HttpEntity<>(params,headers);

        //Http ???????????? - Post???????????? - ????????? Response????????? ?????? ??????.
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
        //Post ???????????? key=value ???????????? ??????(??????????????????)
        RestTemplate rt2 = new RestTemplate();

        //httpHeaders ???????????? ??????
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //httpHeader??? HttpBody??? ????????? ??????????????? ??????
        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);

        //Http ???????????? - Post???????????? - ????????? Response????????? ?????? ??????.
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

        String username = "kakao_"+kakaoProfile.getId();
        User kakao_user = userService.findByUsername(username);

        String rawPassword = bCryptPasswordEncoder.encode(kakaoProfile.getId() + "");

        if (kakao_user == null) {
            KakaoUserDto kakaoUserDto = KakaoUserDto.builder()
                    .username("kakao_" + kakaoProfile.getId())
                    .password(rawPassword)
                    .nickname(kakaoProfile.getKakao_account().getProfile().getNickname())
                    .profilePicture(kakaoProfile.getKakao_account().getProfile().getProfile_image_url())
                    .build();



            userService.saveKakaoUser(kakaoUserDto);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, kakaoProfile.getId() + ""));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";

    }
}
