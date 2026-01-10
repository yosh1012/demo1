import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './tailwind.css'
import { errorMessage } from '../lib/errorMessage';

const rootElement = document.getElementById('root');

if (!rootElement) errorMessage('No root element');

ReactDOM.createRoot(rootElement).render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);