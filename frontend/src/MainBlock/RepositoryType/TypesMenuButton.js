import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import EditIcon from '@material-ui/icons/Edit';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';

import { fetchRepositoryTypes, editRepositoryType } from 'redux/models/Repository/repositoriesActions';
import { adminBadgeIcon } from 'Utils/commonStyles';

const styles = theme => ({
    adminBadgeIcon,
    activeRepositoryType: {
        color: theme.palette.primary.main,
    },
});

function TypesMenuButton({ classes, repository, repositoryTypes, fetchRepositoryTypes, editRepositoryType }) {
    useEffect(() => {
        !repositoryTypes && fetchRepositoryTypes();
    }, []);

    const [anchorEl, setAnchorEl] = useState(null);

    const handleClickOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const onClick = (event) => {
        editRepositoryType({
            ...repository,
            repositoryType: event.target.id,
        });
        handleClose();
    };

    return (
        <Fragment>
            <EditIcon className={classes.adminBadgeIcon} onClick={handleClickOpen} />
            <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={handleClose}>
                {repositoryTypes && repositoryTypes.map(type => (
                    <MenuItem
                        id={type}
                        key={type}
                        onClick={onClick}
                        className={type === repository.repositoryType ? classes.activeRepositoryType : null}
                    >
                        {type}
                    </MenuItem>
                ))}
            </Menu>
        </Fragment>
    );
}

TypesMenuButton.propTypes = {
    classes: PropTypes.object.isRequired,
    repository: PropTypes.object.isRequired,
    repositoryTypes: PropTypes.array,
    fetchRepositoryTypes: PropTypes.func.isRequired,
    editRepositoryType: PropTypes.func.isRequired,
};

export default connect(
    state => ({ repositoryTypes: state.repositories.repositoryTypes }),
    {
        fetchRepositoryTypes,
        editRepositoryType,
    },
)(withStyles(styles)(TypesMenuButton));
