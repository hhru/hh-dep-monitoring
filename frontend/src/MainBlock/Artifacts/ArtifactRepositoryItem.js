import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

import ListItemText from '@material-ui/core/ListItemText';
import { withStyles } from '@material-ui/core/styles';

import { listItemWithoutIcon } from 'Utils/commonStyles';
import ArtfactListItem from './ArtifactListItem';

const styles = () => ({
    listItemWithoutIcon,
});

function ArtifactRepositoryItem({ classes, repository }) {
    return (
        <ArtfactListItem nestedItems={repository.dependencies}>
            { hasAnyChildren => (
                <ListItemText
                    className={classNames({ [classes.listItemWithoutIcon]: !hasAnyChildren })}
                    primary={repository.name}
                    secondary={repository.description}
                />
            )}
        </ArtfactListItem>
    );
}

ArtifactRepositoryItem.propTypes = {
    classes: PropTypes.object.isRequired,
    repository: PropTypes.object.isRequired,
};

export default withStyles(styles)(ArtifactRepositoryItem);
