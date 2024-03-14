import React, { useState, useEffect } from 'react';
import { useParams, useNavigate  } from 'react-router-dom'; // useParams import 추가
import axios from 'axios';
import './content.css';

function ContentPage() {
    const { id } = useParams(); // URL에서 파라미터로 전달된 id 값을 가져오기 위해 useParams 훅 사용
    const [content, setContent] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`http://localhost:8082/diary/content/${id}`)
            .then(response => {
                setContent(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, [id]); // id 값이 변경될 때마다 useEffect가 실행되도록 변경

    // HTML 태그를 제거하여 내용을 표시하는 함수
    function removeHtmlTags(htmlString) {
        if(htmlString != null)
        return htmlString.replace(/<[^>]+>/g, '');
    }

    // 삭제 버튼 클릭 이벤트 핸들러
    const handleDelete = () => {
        axios.get(`http://localhost:8082/diary/delete/${id}`)
            .then(response => {
                // 성공 시 팝업 표시
                alert('삭제 성공');
                // 리스트 페이지로 이동
                navigate('/list');
            })
            .catch(error => {
                // 실패 시 팝업 표시
                alert('삭제 실패');
            });
    };

    // 수정 페이지로 이동
    const handleEdit = () => {
        navigate(`/edit/${id}`);
    }


    return (
        <div>
            <h1>글 자세히 보기 페이지입니다</h1>
            <div className="content">
                <h2>{content.title}</h2>
                <p>{removeHtmlTags(content.content)}</p>
                <button onClick={handleDelete}>삭제</button>
                <button onClick={handleEdit}>수정</button>
            </div>
        </div>
    );
}

export default ContentPage;
