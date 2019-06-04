import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import ListItem from '@material-ui/core/ListItem';
import LinkIcon from '@material-ui/icons/Link';
import LinkOffIcon from '@material-ui/icons/LinkOff';
import Tooltip from '@material-ui/core/Tooltip';

import { relatedRepositoryIcon, repoIconsContainer,
    flexVerticalContainer, flexInlineContainer } from 'Utils/commonStyles';
import RepositoryLinks from 'MainBlock/RepositoryLinks/RepositoryLinks';
import ListItemInfo from './ListItemInfo';
import ListItemTitle from './ListItemTitle';
import CoverageLabel from '../CoverageLabel';

const styles = () => ({
    relatedRepositoryIcon,
    repoIconsContainer,
    repoInfoContainer: {
        ...flexVerticalContainer,
        justifyContent: 'space-between',
    },
    flexInlineContainer,
});

function RepositoryItem({ classes, repository }) {
    const renderIconWithTooltip = (hasFunctionalRelations) => {
        const title = hasFunctionalRelations ? 'Have functional relations' : 'No functional relations';
        const IconComponent = hasFunctionalRelations ? LinkIcon : LinkOffIcon;

        return (
            <Tooltip title={title} placement="left">
                <IconComponent className={classes.relatedRepositoryIcon} />
            </Tooltip>
        );
    };

    return (
        <ListItem className={classes.flexInlineContainer}>
            <div className={classes.repoInfoContainer}>
                <ListItemTitle
                    repositoryId={repository.repositoryId}
                    name={repository.name}
                    archived={repository.archived}
                />
                <ListItemInfo
                    description={repository.description === null ? '' : repository.description}
                    dateUpdate={repository.updatedAt}
                    dateEvent={repository.lastEvent}
                    type={repository.repositoryType}
                />
            </div>
            <div className={classes.repoIconsContainer}>
                <RepositoryLinks repositoryId={repository.repositoryId} size="small" />
                <div className={classes.flexInlineContainer}>
                    {repository.coverage && (<CoverageLabel value={repository.coverage} size="small" />)}
                    {renderIconWithTooltip(repository.hasRelatedFrom || repository.hasRelatedTo)}
                </div>
            </div>
        </ListItem>
    );
}

RepositoryItem.propTypes = {
    classes: PropTypes.object.isRequired,
    repository: PropTypes.object.isRequired,
};

export default withStyles(styles)(RepositoryItem);
