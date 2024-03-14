package com.example.diary_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DiaryEntity {

    @Id // @Id 어노테이션은 엔티티 클래스의 기본 키(primary key)를 나타내서 JPA 가 엔티티를 관리하기 위해서는 기본 키가 필요
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue 어노테이션과 함께 사용하여 자동 증가 전략을 지정, 중복 생성 X
    private int id;

    private String title;
    private String content;

}
