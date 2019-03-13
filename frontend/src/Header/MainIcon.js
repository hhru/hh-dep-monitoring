import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import { fade } from '@material-ui/core/styles/colorManipulator';

const styles = theme => ({
    root: {
        backgroundColor: fade(theme.palette.common.black, 0.4),
        width: theme.spacing.unit * 9,
        height: theme.spacing.unit * 9,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: theme.spacing.unit * 5,
        margin: theme.spacing.unit,
        flexShrink: 0,
        fontSize: theme.spacing.unit * 5,
    },
});

function MainIcon(props) {
    const { classes } = props;
    return (
        <div className={classes.root}>
            dm
        </div>
    );
}

MainIcon.propTypes = {
    classes: PropTypes.string.isRequired,
};

export default withStyles(styles)(MainIcon);
