import React, { useState } from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Badge from '@material-ui/core/Badge';
import IconButton from '@material-ui/core/IconButton';
import SortIcon from '@material-ui/icons/Sort';
import Menu from '@material-ui/core/Menu';

import connect from 'react-redux/es/connect/connect';
import SortingAttributes from './SortingAttributes';

const styles = () => ({
    repositoriesSearchInput: {
        width: '300px',
        marginBottom: '10px',
    },
});

function FetchingParams({ classes, searchString, handleSearchStringChange, handleOrderParamsChange,
    sortAttributes, setSortAttributes }) {
    const [anchorEl, setAnchorEl] = useState(null);

    const handleSortButtonClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleSortMenuClose = () => {
        handleOrderParamsChange();
        setAnchorEl(null);
    };

    return (
        <div>
            <TextField
                className={classes.repositoriesSearchInput}
                label="Search repository"
                value={searchString}
                onChange={handleSearchStringChange}
                type="search"
            />
            <IconButton onClick={handleSortButtonClick}>
                <Badge badgeContent={sortAttributes.length} color="primary">
                    <SortIcon />
                </Badge>
            </IconButton>
            <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={handleSortMenuClose}>
                <SortingAttributes
                    sortedAttributes={sortAttributes}
                    setSortedAttributes={setSortAttributes}
                />
            </Menu>
        </div>
    );
}

FetchingParams.propTypes = {
    classes: PropTypes.object.isRequired,
    searchString: PropTypes.string.isRequired,
    handleSearchStringChange: PropTypes.func.isRequired,
    handleOrderParamsChange: PropTypes.func.isRequired,
    sortAttributes: PropTypes.array.isRequired,
    setSortAttributes: PropTypes.func.isRequired,
};

export default connect(
    state => ({ searchString: state.repositories.searchString }),
)(withStyles(styles)(FetchingParams));
