import React, { useState, useEffect } from 'react';
import { useParams, useNavigate  } from 'react-router-dom'; // useParams import 추가
import axios from 'axios';
import './edit.css';
import {CKEditor} from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";

function EditPage() {
    const { id } = useParams(); // URL에서 파라미터로 전달된 id 값을 가져오기 위해 useParams 훅 사용
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`http://localhost:8082/diary/edit/${id}`)
            .then(response => {
                setTitle(response.data.title);
                setContent(response.data.content);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, [id]); // id 값이 변경될 때마다 useEffect가 실행되도록 변경

    // 수정 버튼 클릭 이벤트 핸들러
    const handleUpdate = async (event) => {
        event.preventDefault();

        const diaryData = {
            title: title,
            content: content
        };

        try {
            const response = await axios.post(`http://localhost:8082/diary/editor/update/${id}`, diaryData);
            if (response.status === 200) {
                alert('수정 성공');
                navigate('/list'); // 리스트 페이지로 이동
            } else {
                console.error('글 작성 실패');
            }
        } catch (error) {
            console.error('에러: ', error);
            alert('수정 실패');
        }
    };

    const handleChangeTitle = (e) => {
        setTitle(e.target.value);
    };

    const handleChangeContent = (event, editor) => {
        const data = editor.getData();
        setContent(data);
    };


    return (
        <div className="container">
            <h3>글 수정 페이지입니다</h3>
            <form onSubmit={handleUpdate}>
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
                <br/>
                <button type="submit">수정 완료</button>
            </form>
        </div>

    );
}

export default EditPage;
