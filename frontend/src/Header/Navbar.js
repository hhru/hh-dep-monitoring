import React from 'react';
import PropTypes from 'prop-types';
import { styled } from '@material-ui/styles';
import { Link } from 'react-router-dom';

import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';

import { routing } from '../MainBlock/MainBlock';

const styles = {
    root: {
        flexGrow: 1,
    },
};

const StyledLink = styled(Link)({
    textDecoration: 'none',
    color: '#fff',
});

function Navbar({ classes }) {
    return (
        <div className={classes.root}>
            {routing && routing.map(item => (
                <Button key={item.label} color="inherit">
                    <StyledLink className={classes.link} to={item.path}>{item.label}</StyledLink>
                </Button>
            ))}
        </div>
    );
}

Navbar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Navbar);
