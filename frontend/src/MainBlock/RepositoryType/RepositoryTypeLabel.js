import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import Chip from '@material-ui/core/Chip';
import Library from '@material-ui/icons/LocalLibrary';
import Config from '@material-ui/icons/Settings';
import Service from '@material-ui/icons/SettingsApplications';
import Application from '@material-ui/icons/PhonelinkSetup';
import Other from '@material-ui/icons/MoreHoriz';

import { chip } from 'Utils/commonStyles';

const styles = () => ({
    repositoryTypeMark: {
        ...chip,
        marginLeft: '-2px',
    },
    repositoryTypeBigMark: {
        marginLeft: '12px',
    },
    chipIcon: {
        fontSize: 20,
    },
});

const repositoryTypeIcons = {
    SERVICE: Service,
    LIBRARY: Library,
    CONFIG: Config,
    APPLICATION: Application,
    OTHER: Other,
};

function RepositoryTypeLabel({ classes, type, big }) {
    const TypeIcon = repositoryTypeIcons[type];
    return (
        <Chip
            icon={<TypeIcon className={classes.chipIcon} />}
            label={type}
            className={big ? classes.repositoryTypeBigMark : classes.repositoryTypeMark}
        />
    );
}

RepositoryTypeLabel.propTypes = {
    classes: PropTypes.object.isRequired,
    type: PropTypes.string.isRequired,
    big: PropTypes.bool,
};

export default withStyles(styles)(RepositoryTypeLabel);
