import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

import { withStyles } from '@material-ui/core/styles';
import MenuItem from '@material-ui/core/MenuItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ArrowIcon from '@material-ui/icons/KeyboardBackspace';

const styles = theme => ({
    activeAttributeItem: {
        color: theme.palette.primary.main,
    },
    attributeItem: {
        width: '200px',
        '&:hover': {
            color: theme.palette.primary.main,
        },
    },
    icon: {
        color: theme.palette.primary.main,
        marginRight: '0px',
    },
    iconUp: {
        transform: 'rotate(90deg)',
    },
    iconDown: {
        transform: 'rotate(270deg)',
    },
});

function SortingAttribute({ classes, name, label, state, handleClick }) {
    const onClick = () => handleClick(name);

    return (
        <MenuItem
            onClick={onClick}
            className={classNames(classes.attributeItem, { [classes.activeAttributeItem]: state })}
        >
            <ListItemText>{label}</ListItemText>
            {state && (
                <ListItemIcon className={classNames(classes.icon, state === 'asc' ? classes.iconUp : classes.iconDown)}>
                    <ArrowIcon />
                </ListItemIcon>
            )}
        </MenuItem>
    );
}

SortingAttribute.propTypes = {
    classes: PropTypes.object.isRequired,
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    state: PropTypes.string,
    handleClick: PropTypes.func.isRequired,
};

export default withStyles(styles)(SortingAttribute);
