package com.example.diary_back.service;

import com.example.diary_back.dto.SaveDto;
import com.example.diary_back.entity.DiaryEntity;
import com.example.diary_back.respoitory.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {

    private ContentRepository contentRepository;

    @Autowired
    public DiaryService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    // 글 저장 메소드
    public int saveContent(SaveDto saveDto) {

        // DTO 에서 꺼낸 뒤에
        String title = saveDto.getTitle();
        String content = sanitizeContent(saveDto.getContent());

        // 엔티티에 집어넣어줌
        // 참고로 set 은 추천하지 않아서 실무에서는 생성자를 통해 초기화 해주기
        DiaryEntity diaryEntity = new DiaryEntity();

        diaryEntity.setTitle(title);
        diaryEntity.setContent(content);

        contentRepository.save(diaryEntity); // save 메소드가 제공됨

        // 저장 시도
        try {
            contentRepository.save(diaryEntity);
            return 1; // 성공 시 1 반환
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 실패 시 0 반환
        }
    }

    // HTML 태그를 제거 함수
    private String sanitizeContent(String content) {
        // HTML 태그 제거 또는 이스케이프 처리 로직 추가
        return content.replaceAll("\\<.*?\\>", ""); // 예시로 HTML 태그 제거
    }

    public List<DiaryEntity> selectContent() {

        return contentRepository.findAll();
    }


    // id로 일기 찾는 메소드
    public DiaryEntity getContentById(int id) {
        Optional<DiaryEntity> optionalDiary = contentRepository.findById(id);
        return optionalDiary.orElse(null); // 만약 optionalDiary가 비어있으면 null 반환
    }

    public int deleteOneContent(int id) {
        // 삭제 시도
        try {
            contentRepository.deleteById(id);
            return 1; // 성공 시 1 반환
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 실패 시 0 반환
        }
    }

    // 글 수정 메소드
    public int updateOneContent(SaveDto saveDTO , int id) {

        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setId(id);
        diaryEntity.setTitle(saveDTO.getTitle());
        diaryEntity.setContent(sanitizeContent(saveDTO.getContent()));

        // 수정 시도
        try {
            contentRepository.save(diaryEntity);
            return 1; // 성공 시 1 반환
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 실패 시 0 반환
        }
    }
}
