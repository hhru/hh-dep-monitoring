import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import LinkIcon from '@material-ui/icons/Link';
import LinkOffIcon from '@material-ui/icons/LinkOff';
import Tooltip from '@material-ui/core/Tooltip';

import { relatedRepositoryIcon, repoIconsContainer } from 'Utils/commonStyles';
import RepositoryLinks from 'MainBlock/RepositoryLinks/RepositoryLinks';
import ListItemInfo from './ListItemInfo';
import ListItemTitle from './ListItemTitle';

const styles = () => ({
    relatedRepositoryIcon,
    repoIconsContainer,
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
        <ListItem>
            <ListItemText
                primary={(
                    <ListItemTitle
                        repositoryId={repository.repositoryId}
                        name={repository.name}
                        archived={repository.archived}
                    />
                )}
                secondary={(
                    <ListItemInfo
                        descr={repository.description === null ? '' : repository.description}
                        date={repository.updatedAt}
                    />
                )}
            />
            <div className={classes.repoIconsContainer}>
                <RepositoryLinks repositoryId={repository.repositoryId} size="small" />
                {renderIconWithTooltip(repository.hasRelatedFrom || repository.hasRelatedTo)}
            </div>
        </ListItem>
    );
}

RepositoryItem.propTypes = {
    classes: PropTypes.object.isRequired,
    repository: PropTypes.object.isRequired,
};

export default withStyles(styles)(RepositoryItem);
