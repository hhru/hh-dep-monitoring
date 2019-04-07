import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import connect from 'react-redux/es/connect/connect';

import Paper from '@material-ui/core/Paper';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';

import { fetchRepositoryById } from 'redux/models/Repository/repositoriesActions';
import { descriptionListItem } from 'Utils/commonStyles';
import Relations from './Relations';
import RepositoryHeader from './RepositoryHeader';

const styles = theme => ({
    root: {
        padding: theme.spacing.unit * 2,
        width: theme.spacing.unit * 80,
    },
    descriptionListItem,
});

function Repository({ classes, match, repository, fetchRepositoryById }) {
    const { repositoryId } = match.params;

    useEffect(() => {
        fetchRepositoryById(repositoryId);
    }, [repositoryId]);

    return (
        <Paper className={classes.root}>
            {repository && (
                <Fragment>
                    <RepositoryHeader repository={repository} />
                    <div className={classes.descriptionListItem}>
                        {repository.description}
                    </div>
                    <Typography variant="title">
                        Relations
                    </Typography>
                    <Relations repositoryId={repositoryId} />
                    <Divider />
                </Fragment>
            )}
        </Paper>
    );
}

Repository.propTypes = {
    classes: PropTypes.object.isRequired,
    match: PropTypes.object.isRequired,
    repository: PropTypes.object.isRequired,
    fetchRepositoryById: PropTypes.func.isRequired,
};

export default connect(
    (state, ownProps) => ({
        repository: state.repositories.repositoryById[ownProps.match.params.repositoryId],
    }),
    { fetchRepositoryById },
)(withStyles(styles)(Repository));
