import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import { withStyles } from '@material-ui/core/styles';

import { fetchArtifactsTree } from 'redux/models/Artifact/artifactsActions';
import { genericPaper, flexInlineContainer } from 'Utils/commonStyles';
import ArtifactRepositoryItem from './ArtifactRepositoryItem';
import ArtifactsSelect from './ArtifactsSelect';

const styles = theme => ({
    genericPaper: {
        ...genericPaper(theme),
    },
    artifactsHeader: {
        ...flexInlineContainer,
        [theme.breakpoints.down('sm')]: {
            flexDirection: 'column',
            alignItems: 'flex-start',
        },
    },
});

const searchParamsInNode = (searchingParam, paramName, node, path, result) => {
    if (String(node.artifactVersion.artifact[paramName]) === searchingParam) {
        result.push(...path);
        return;
    }
    if (!node.children || node.children.length === 0) {
        return;
    }
    node.children.forEach((child) => {
        path.push(child.artifactVersion.artifact[paramName]);
        searchParamsInNode(searchingParam, paramName, child, path, result);
        path.pop();
    });
};

const searchParamInTree = (searchingParam, paramName, tree) => {
    const searchIdResults = {};
    tree.forEach((repository) => {
        const result = [];
        const path = [];
        repository.dependencies.forEach((dependency) => {
            path.push(dependency.artifactVersion.artifact[paramName]);
            searchParamsInNode(searchingParam, paramName, dependency, path, result);
            path.pop();
        });
        result.length !== 0 && (searchIdResults[repository.repositoryId] = [...result]);
    });
    return searchIdResults;
};

function Artifacts({ classes, artifactTree, fetchArtifactsTree, match }) {
    useEffect(() => {
        artifactTree.length === 0 && fetchArtifactsTree();
    }, []);

    const { searchingParam, paramName } = match.params;
    const allActiveIds = (artifactTree && searchingParam)
        ? (searchParamInTree(searchingParam, paramName, artifactTree))
        : {};

    return (
        <Paper className={classes.genericPaper}>
            <div className={classes.artifactsHeader}>
                <Typography variant="h4">
                    Artifacts
                    {!artifactTree && ' not found'}
                </Typography>
                <ArtifactsSelect />
            </div>
            {artifactTree && (
                <List>
                    {artifactTree.map(repository => (
                        <Fragment key={repository.repositoryId}>
                            <ArtifactRepositoryItem
                                repository={repository}
                                searchParams={{
                                    open: Object.keys(allActiveIds).indexOf(String(repository.repositoryId)) !== -1,
                                    pathToId: allActiveIds[repository.repositoryId],
                                    level: 0,
                                    paramName,
                                }}
                            />
                            <Divider />
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
    fetchArtifactsTree: PropTypes.func.isRequired,
    match: PropTypes.object.isRequired,
};

export default connect(
    state => ({ artifactTree: state.artifacts.tree }),
    { fetchArtifactsTree },
)(withStyles(styles)(Artifacts));
