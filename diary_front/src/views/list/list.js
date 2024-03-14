import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './list.css';

function ListPage() {
    const [contentList, setContentList] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8082/diary/listAll')
            .then(response => {
                setContentList(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    return (
        <div className="list-container">
            <h1>목록페이지입니다</h1>
            <ul>
                {contentList.map((content, index) => (
                    <li className="list-item" key={index}>
                        <button className="list-button">
                            <a href={`/content/${content.id}`} className="list-link">{content.id}번 : {content.title}</a>
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default ListPage;
