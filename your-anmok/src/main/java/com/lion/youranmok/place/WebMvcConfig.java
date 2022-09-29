package com.lion.youranmok.place;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/placeimg/**")
                .addResourceLocations("file:///" + genFileDirPath  + "/");
    }
}
