package com.lion.youranmok.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lion.youranmok.login.dto.KakaoUserDto;
import com.lion.youranmok.login.entity.BaseTimeEntity;
import com.lion.youranmok.login.model.KakaoProfile;
import com.lion.youranmok.login.model.OauthToken;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    @Value("${Kakao_Client}")
    private String Kakao_Client;
    @Value("${Kakao_Callback}")
    private String Kakao_Callback;

    public void kakaoToken(String code) throws JsonProcessingException {
        BaseTimeEntity baseTimeEntity = new BaseTimeEntity();
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", Kakao_Client);
        params.add("redirect_uri", Kakao_Callback);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        oauthToken = objectMapper.readValue(response.getBody(), OauthToken.class);

        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (
                JsonMappingException e) {
            e.printStackTrace();
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }

        String username = "kakao_" + kakaoProfile.getId();
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
    }
}
