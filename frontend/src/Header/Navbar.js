import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';

import { routing } from 'MainBlock/MainBlock';
import { buttonLink } from 'Utils/commonStyles';

const styles = {
    root: {
        flexGrow: 1,
    },
    buttonLink,
};

function Navbar({ classes }) {
    return (
        <div className={classes.root}>
            {routing && routing.map(item => (
                <Button key={item.label} color="inherit">
                    <Link className={classes.buttonLink} to={item.path}>{item.label}</Link>
                </Button>
            ))}
        </div>
    );
}

Navbar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Navbar);
