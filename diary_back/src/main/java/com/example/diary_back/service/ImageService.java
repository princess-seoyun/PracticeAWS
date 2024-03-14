package com.example.diary_back.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.diary_back.config.S3Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    private S3Config s3Config;

    @Autowired
    public ImageService(S3Config s3Config) {

        this.s3Config = s3Config;

    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private String localLocation = "C:\\Users\\user\\Desktop\\diary_s3\\"; // 바탕화면에 이미지 저장할 폴더 하나 만들었음, 참고로 맨 끝에 \\ 넣어주기


    public String imageUpload(MultipartRequest request) throws IOException {

        MultipartFile file = request.getFile("upload"); // upload 키에 이미지가 저장되어있어서 upload 에서 꺼내는 것

        String fileName = file.getOriginalFilename(); // file 의 파일명을 꺼냄
        String ext = fileName.substring(fileName.indexOf(".")); // 점으로 구분된 확장자를 가져옴

        String uuidFileName = UUID.randomUUID() + ext; // 동일한 이름으로 올라갈 경우 이미지 파일이 덮여지거나 누락될 수 있어서 고유 UUID 로 식별번호를 부여해줌
        String localPath =  localLocation + uuidFileName; // 이 서버 환경에 저장한 후 S3 에 올려야 해서 서버 주소를 지정

        // 서버 환경에 저장
        File localFile = new File(localPath); // 새로운 파일 객체 생성
        file.transferTo(localFile); // 로컬 경로에 이미지를 저장

        // S3 에 이미지 올림
//        s3Config.amazonS3Client().putObject(new PutObjectRequest("버킷 이름", "파일명","서버에 저장한 파일"))
        s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, uuidFileName , localFile).withCannedAcl(CannedAccessControlList.PublicRead));
        String s3Url = s3Config.amazonS3Client().getUrl(bucket,uuidFileName).toString();

        // 서버 환경에 저장한 이미지 삭제
        localFile.delete();

        return s3Url;
    }

}
