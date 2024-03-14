package com.example.diary_back.controller;

import com.example.diary_back.entity.DiaryEntity;
import com.example.diary_back.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/diary")
public class ContentController {

    private DiaryService diaryService;

    @Autowired
    public ContentController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/content/{id}")
    @ResponseBody
    public DiaryEntity contentP(@PathVariable("id") int id) {

        DiaryEntity diaryEntity = diaryService.getContentById(id);

        return diaryEntity;
    }
}
