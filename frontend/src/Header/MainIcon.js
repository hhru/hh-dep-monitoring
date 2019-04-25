import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import { withStyles } from '@material-ui/core/styles';

const styles = theme => ({
    root: {
        backgroundColor: '#fff',
        width: theme.spacing.unit * 9,
        height: theme.spacing.unit * 9,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: theme.spacing.unit * 5,
        margin: theme.spacing.unit,
        flexShrink: 0,
        fontSize: theme.spacing.unit * 5,
        color: theme.palette.primary.main,
        textDecoration: 'none',
    },
});

function MainIcon({ classes }) {
    return (
        <Link className={classes.root} to="/">
            dm
        </Link>
    );
}

MainIcon.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(MainIcon);
