import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Tooltip from '@material-ui/core/Tooltip';

import { archiveIconBig } from 'Utils/commonStyles';
import Icon from 'Utils/icons';
import AdminBadge from 'CommonComponents/AdminBadge';
import TypesMenuButton from 'MainBlock/RepositoryType/TypesMenuButton';
import RepositoryTypeLabel from 'MainBlock/RepositoryType/RepositoryTypeLabel';
import ListItemDate from './ListItemDate';

const styles = theme => ({
    repositoryHeader: {
        display: 'flex',
        justifyContent: 'space-between',
        [theme.breakpoints.down('sm')]: {
            flexDirection: 'column',
        },
    },
});

function RepositoryHeader({ classes, repository }) {
    return (
        <div className={classes.repositoryHeader}>
            <Typography variant="h4">
                {repository.name}
                <AdminBadge badgeContent={<TypesMenuButton repository={repository} />}>
                    <RepositoryTypeLabel type={repository.repositoryType} big />
                </AdminBadge>
                {repository.archived && (
                    <Tooltip title="This is an archived repository" enterDelay={300}>
                        <span>
                            <Icon iconName="archive" styles={archiveIconBig} />
                        </span>
                    </Tooltip>
                )}
            </Typography>
            <ListItemDate created={repository.createdAt} updated={repository.updatedAt} />
        </div>
    );
}

RepositoryHeader.propTypes = {
    classes: PropTypes.object.isRequired,
    repository: PropTypes.object.isRequired,
};

export default withStyles(styles)(RepositoryHeader);
