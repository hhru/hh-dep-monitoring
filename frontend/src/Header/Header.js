import React from 'react';

import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';

import MainIcon from './MainIcon';
import Navbar from './Navbar';
import AdminModeSwitch from './AdminModeSwitch';

export default function Header() {
    return (
        <AppBar position="static">
            <Toolbar>
                <MainIcon />
                <Navbar />
                <AdminModeSwitch />
            </Toolbar>
        </AppBar>
    );
}
