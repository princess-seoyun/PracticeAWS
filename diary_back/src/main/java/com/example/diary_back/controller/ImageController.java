package com.example.diary_back.controller;

import com.example.diary_back.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/diary")
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/image/upload")
    @ResponseBody
    public Map<String, Object> imageUpload(MultipartRequest request) throws Exception { // MultipartRequest 는 웹 애플리케이션에서 파일 업로드와 함께 텍스트 데이터를 전송하는 데 사용되는 HTTP 요청의 한 유형


        Map<String, Object> responseData = new HashMap<>();

        try {
            String s3Url = imageService.imageUpload(request);

            responseData.put("uploaded", true); // 업로드 성공
            responseData.put("url", s3Url);

        } catch (IOException e) {
            responseData.put("uploaded", false); // 업로드 실패

            return  responseData;
        }

        return responseData;

    }
}
