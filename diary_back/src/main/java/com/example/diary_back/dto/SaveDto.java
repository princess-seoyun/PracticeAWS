package com.example.diary_back.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SaveDto {

    private String title; // 제목

    private String content; // 내용

}
