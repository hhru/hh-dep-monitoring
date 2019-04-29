import React from 'react';
import { SnackbarProvider } from 'notistack';

import { MuiThemeProvider } from '@material-ui/core/styles';

import './App.css';
import Header from './Header/Header';
import MainBlock from './MainBlock/MainBlock';
import Notifier from './CommonComponents/Notifier';
import MasterTheme from './MaterialTheme';

export default function App() {
    return (
        <MuiThemeProvider theme={MasterTheme}>
            <SnackbarProvider>
                <Header />
                <MainBlock />
                <Notifier />
            </SnackbarProvider>
        </MuiThemeProvider>
    );
}
