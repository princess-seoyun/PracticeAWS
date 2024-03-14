package com.example.diary_back.controller;

import com.example.diary_back.dto.SaveDto;
import com.example.diary_back.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class SaveController {

    private DiaryService diaryService;

    @Autowired
    public SaveController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveLogic(@RequestBody SaveDto saveDto) {
        int result = diaryService.saveContent(saveDto);
        if (result == 1) { // 참고로 서비스에서 결과를 int 로 리턴 받도록 코딩해둬서 . . .
            return ResponseEntity.ok("성공"); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패"); // 500 Internal Server Error
        }
    }
}
