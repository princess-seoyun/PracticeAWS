package com.example.diary_back.respoitory;

import com.example.diary_back.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<DiaryEntity, Integer> {
}
