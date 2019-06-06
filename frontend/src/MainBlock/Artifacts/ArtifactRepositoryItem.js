import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import { withStyles } from '@material-ui/core/styles';

import { flexVerticalContainer, repositoryItemTitle, githubIcon, genericLink,
    descriptionListItem } from 'Utils/commonStyles';
import Icon from 'Utils/icons';
import ArtfactListItem from './ArtifactListItem';

const styles = () => ({
    flexVerticalContainer,
    repositoryItemTitle: {
        ...repositoryItemTitle,
        textDecoration: 'none',
        '&:hover': {
            textDecoration: 'underline',
        },
    },
    genericLink,
    descriptionListItem,
});

function ArtifactRepositoryItem({ classes, repository, searchParams }) {
    return (
        <ArtfactListItem nestedItems={repository.dependencies} searchParams={searchParams}>
            { () => (
                <div className={classes.flexVerticalContainer}>
                    <Link
                        to={`/repositories/${repository.repositoryId}`}
                        className={classes.repositoryItemTitle}
                    >
                        <Icon iconName="github" styles={githubIcon} />
                        {repository.name}
                    </Link>
                    <div className={classes.descriptionListItem}>
                        {!!repository.description && repository.description}
                    </div>
                </div>
            )}
        </ArtfactListItem>
    );
}

ArtifactRepositoryItem.propTypes = {
    classes: PropTypes.object.isRequired,
    repository: PropTypes.object.isRequired,
    searchParams: PropTypes.object,
};

export default withStyles(styles)(ArtifactRepositoryItem);
