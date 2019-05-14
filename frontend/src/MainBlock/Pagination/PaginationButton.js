import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core';
import IconButton from '@material-ui/core/IconButton';

import { pageButton } from 'Utils/commonStyles';

const styles = () => ({
    pageButton,
});

function PaginationButton({ classes, page, active, disabled, onClick }) {
    const handleClick = () => {
        onClick && onClick(page);
    };
    return (
        <IconButton
            className={classes.pageButton}
            color={active ? 'primary' : 'default'}
            disabled={disabled}
            onClick={handleClick}
        >
            {typeof page === 'undefined' ? '..' : page + 1}
        </IconButton>
    );
}

PaginationButton.propTypes = {
    classes: PropTypes.object.isRequired,
    page: PropTypes.number,
    active: PropTypes.bool,
    disabled: PropTypes.bool,
    onClick: PropTypes.func,
};

export default withStyles(styles)(PaginationButton);
