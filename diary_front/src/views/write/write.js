import React, { Component } from 'react';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import './write.css';
import axios from 'axios'; // axios 추가
import {useNavigate} from "react-router-dom";

function WritePage() {
    const [title, setTitle] = React.useState('');
    const [content, setContent] = React.useState('');
    const navigate = useNavigate();

    const handleChangeTitle = (e) => {
        setTitle(e.target.value);
    };

    const handleChangeContent = (event, editor) => {
        const data = editor.getData();
        setContent(data);
    };

    const handleSubmit = async (event) => {


        event.preventDefault();

        const diaryData = {
            title: title,
            content: content
        };

        try {
            const response = await axios.post('http://localhost:8082/diary/save', diaryData);
            if (response.status === 200) {
                console.log('글 작성 성공');
                navigate('/main'); // 메인 페이지로 이동
            } else {
                console.error('글 작성 실패');
            }
        } catch (error) {
            console.error('에러: ', error);
        }
    };

    return (
        <div className="container">
            <h3>일기 작성하기</h3>
            <form onSubmit={handleSubmit}>
                <div className="textarea-container">
                    <label>제목: </label>
                    <input
                        type="text"
                        value={title}
                        onChange={handleChangeTitle}
                        placeholder="제목을 입력하세요."
                    />
                </div>
                <div className="textarea-container">
                    <CKEditor
                        editor={ClassicEditor}
                        data={content}
                        onChange={handleChangeContent}
                        config={{
                            ckfinder: {
                                uploadUrl: 'http://localhost:8082/diary/image/upload',
                                withCredentials: true
                            }
                        }}
                    />
                </div>
                <br />
                <button type="submit">작성 완료</button>
            </form>
        </div>
    );
}

export default WritePage;
