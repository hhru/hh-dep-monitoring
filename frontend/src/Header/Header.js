import React from 'react';

import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';

import MainIcon from './MainIcon';
import UserBlock from './UserBlock';
import Navbar from './Navbar';
import Search from './Search';

export default function Header() {
    return (
        <AppBar position="static">
            <Toolbar>
                <MainIcon />
                <Navbar />
                <Search />
                <UserBlock account={{ accountName: 'SuhoyVasya' }} />
            </Toolbar>
        </AppBar>
    );
}
