import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Divider from '@material-ui/core/Divider';
import List from '@material-ui/core/List';
import Paper from '@material-ui/core/Paper';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';

import RepositoryItem from './RepositoryItem';
import { fetchRepositories } from '../../redux/repositoriesActions';

const styles = theme => ({
    root: {
        padding: theme.spacing.unit * 2,
        width: theme.spacing.unit * 50,
    },
});

function Repositories({ classes, repositories, fetchRepositories }) {
    useEffect(() => {
        fetchRepositories();
    }, []);

    return (
        <Paper className={classes.root}>
            <Typography variant="h4">
                Repositories
            </Typography>
            <List>
                {repositories.items && repositories.items.map(repository => (
                    <Fragment key={repository.repositoryId}>
                        <RepositoryItem repository={repository} />
                        <Divider />
                    </Fragment>
                ))}
            </List>
        </Paper>
    );
}

Repositories.propTypes = {
    classes: PropTypes.object.isRequired,
    repositories: PropTypes.object.isRequired,
    fetchRepositories: PropTypes.func.isRequired,
};

export default connect(
    state => ({ repositories: state.repositories }),
    { fetchRepositories },
)(withStyles(styles)(Repositories));