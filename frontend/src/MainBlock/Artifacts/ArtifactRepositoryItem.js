import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';

import { listItemWithoutIcon } from 'Utils/commonStyles';
import ArtfactListItem from './ArtifactListItem';
import ListItemTitle from "../Repositories/ListItemTitle";
import ListItemInfo from "../Repositories/ListItemInfo";

const styles = () => ({
    listItemWithoutIcon,
});

function ArtifactRepositoryItem({ classes, repository }) {
    return (
        <ArtfactListItem nestedItems={repository.dependencies}>
            { hasAnyChildren => (
                <div className={classes.flexInlineContainer}>
                    <div className={classes.repoInfoContainer}>
                    <ListItemTitle
                        repositoryId={repository.repositoryId}
                        name={repository.name}
                        archived={repository.archived}
                    />
                    <ListItemInfo
                        description={repository.description === null ? '' : repository.description}
                        dateUpdate={repository.updatedAt}
                        type={repository.repositoryType}
                    />
                    </div>
                </div>
            )}
        </ArtfactListItem>
    );
}

ArtifactRepositoryItem.propTypes = {
    classes: PropTypes.object.isRequired,
    repository: PropTypes.object.isRequired,
};

export default withStyles(styles)(ArtifactRepositoryItem);
