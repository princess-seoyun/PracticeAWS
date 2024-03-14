package com.example.diary_back.controller;

import com.example.diary_back.entity.DiaryEntity;
import com.example.diary_back.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/diary")
public class ListController {

    private DiaryService diaryService;

    @Autowired
    public ListController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/listAll")
    @ResponseBody
    public List<DiaryEntity> listP() {
        return diaryService.selectContent();
    }

}
