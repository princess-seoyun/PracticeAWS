package com.example.diary_back.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;


    @Bean
    public AmazonS3Client amazonS3Client() {

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey); // AWS 액세스 키와 시크릿 키를 사용하여 AWS 자격 증명(BasicAWSCredentials 객체)을 생성

        return (AmazonS3Client) AmazonS3ClientBuilder // 빌더 형식으로
                .standard() // 표준 Amazon S3 클라이언트를 생성
                .withRegion(region) // 클라이언트를 생성할 때 사용할 AWS 리전을 설정
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)) // AWS 자격 증명 프로바이더를 설정하여 클라이언트가 사용할 자격 증명을 제공
                .build();

    }
}