package com.example.diary_back.controller;

import com.example.diary_back.entity.DiaryEntity;
import com.example.diary_back.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/diary")
public class DeleteController {

    private DiaryService diaryService;

    @Autowired
    public DeleteController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteP(@PathVariable("id") int id) {
        int result = diaryService.deleteOneContent(id);
        if (result == 1) {
            return ResponseEntity.ok("성공"); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패"); // 500 Internal Server Error
        }
    }
}
