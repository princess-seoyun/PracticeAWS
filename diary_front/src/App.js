import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import WritePage from './views/write/write.js';
import MainPage from './views/main/main.js';

function App() {
    return (
        <div className='App'>
            <Router>
                <Routes>
                    <Route path="/write" element={<WritePage />} />
                    <Route path="/main" element={<MainPage />} />
                </Routes>
            </Router>
        </div>
    );
}

export default App;
