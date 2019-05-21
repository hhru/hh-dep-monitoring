import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import { withStyles } from '@material-ui/core/styles';

import { fetchArtifactTree } from 'redux/models/Artifact/artifactsActions';
import { genericPaper } from 'Utils/commonStyles';
import ArtifactRepositoryItem from './ArtifactRepositoryItem';

const styles = genericPaper;

function Artifacts({ classes, artifactTree, fetchArtifactTree }) {
    useEffect(() => {
        artifactTree.length === 0 && fetchArtifactTree();
    }, []);

    return (
        <Paper className={classes.root}>
            <Typography variant="h4">
                Artifacts
                {!artifactTree && ' not found'}
            </Typography>
            {artifactTree && (
                <List>
                    {artifactTree.map(repository => (
                        <Fragment key={repository.repositoryId}>
                            <Divider />
                            <ArtifactRepositoryItem repository={repository} />
                        </Fragment>
                    ))}
                </List>
            )}
        </Paper>
    );
}

Artifacts.propTypes = {
    classes: PropTypes.object.isRequired,
    artifactTree: PropTypes.array,
    fetchArtifactTree: PropTypes.func.isRequired,
};

export default connect(
    state => ({ artifactTree: state.artifacts.tree }),
    { fetchArtifactTree },
)(withStyles(styles)(Artifacts));
