package com.example.diary_back.controller;

import com.example.diary_back.dto.SaveDto;
import com.example.diary_back.entity.DiaryEntity;
import com.example.diary_back.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/diary")
public class EditorController {

    private DiaryService diaryService;

    @Autowired
    public EditorController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 수정하려는 글 가져오기
    @GetMapping("/edit/{id}")
    @ResponseBody
    public DiaryEntity editP(@PathVariable("id") int id) {

        DiaryEntity diaryEntity = diaryService.getContentById(id);

        return diaryEntity;
    }

    // 수정한 글을 업데이트 하기
    // 참고로 save 메소드 사용하면 됨
    // 엔티티의 기본 키가 이미 존재하면 해당 엔티티를 수정하고, 그렇지 않으면 새로운 엔티티를 저장함
    @PostMapping("/editor/update/{id}")
    public ResponseEntity<String> editSuccess(@RequestBody SaveDto saveDto, @PathVariable("id") int id) {
        int result = diaryService.updateOneContent(saveDto, id);
        if (result == 1) {
            return ResponseEntity.ok("성공"); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("실패"); // 500 Internal Server Error
        }
    }

}
