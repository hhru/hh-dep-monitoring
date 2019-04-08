import React from 'react';
import { SnackbarProvider } from 'notistack';

import './App.css';
import Header from './Header/Header';
import MainBlock from './MainBlock/MainBlock';
import Notifier from './CommonComponents/Notifier';

export default function App() {
    return (
        <SnackbarProvider>
            <Header />
            <MainBlock />
            <Notifier />
        </SnackbarProvider>
    );
}
