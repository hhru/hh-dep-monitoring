import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Paper from '@material-ui/core/Paper';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';

import { fetchRepository } from 'redux/models/Repository/repositoriesActions';
import { resetFormResult } from 'redux/models/Relation/relationsActions';
import { addMessage } from 'redux/models/Notification/notificationsActions';
import { descriptionListItem, relationsHeader, relationsTitle } from 'Utils/commonStyles';
import AddRelationButton from './RelationForm/OpenFormButton';
import Relations from './Relations';
import RepositoryHeader from './RepositoryHeader';

const styles = theme => ({
    root: {
        padding: theme.spacing.unit * 2,
        width: theme.spacing.unit * 80,
    },
    descriptionListItem,
    relationsHeader,
    relationsTitle,
});

function Repository({ classes, match, adminMode, repository, fetchRepository,
    formResult, resetFormResult, addMessage }) {
    const { repositoryId } = match.params;

    useEffect(() => {
        fetchRepository(repositoryId);
    }, [repositoryId]);

    useEffect(() => {
        if (!formResult) {
            return;
        }
        if (formResult.success) {
            addMessage(`Relation was successfully ${formResult.action}`, 'success');
        } else {
            addMessage('Something went wrong', 'error');
        }
        resetFormResult();
    }, [formResult]);

    return (
        <Paper className={classes.root}>
            {repository && (
                <Fragment>
                    <RepositoryHeader repository={repository} />
                    <div className={classes.descriptionListItem}>
                        {repository.description}
                    </div>
                    <div className={classes.relationsHeader}>
                        <Typography variant="h5" className={classes.relationsTitle}>Relations</Typography>
                        {adminMode && (
                            <AddRelationButton
                                repositoryId={repositoryId}
                                repositoryName={repository.name}
                            />
                        )}
                    </div>
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
    adminMode: PropTypes.bool.isRequired,
    repository: PropTypes.object,
    fetchRepository: PropTypes.func.isRequired,
    formResult: PropTypes.object,
    resetFormResult: PropTypes.func.isRequired,
    addMessage: PropTypes.func.isRequired,
};

export default connect(
    (state, ownProps) => ({
        adminMode: state.tools.adminMode,
        repository: state.repositories.repositoryById[ownProps.match.params.repositoryId],
        formResult: state.relations.formResult,
    }),
    {
        fetchRepository,
        resetFormResult,
        addMessage,
    },
)(withStyles(styles)(Repository));
