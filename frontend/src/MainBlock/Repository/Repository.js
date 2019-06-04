import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Paper from '@material-ui/core/Paper';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';

import { fetchRepository } from 'redux/models/Repository/repositoriesActions';
import { resetFormResult, addMessage } from 'redux/models/Notification/notificationsActions';
import { genericPaper, descriptionListItem, relationsHeader, relationsTitle } from 'Utils/commonStyles';
import RepositoryLinks from 'MainBlock/RepositoryLinks/RepositoryLinks';
import AddRelationButton from './RelationForm/OpenFormButton';
import Relations from './Relations';
import RepositoryHeader from './RepositoryHeader';
import AddLinkButton from '../RepositoryLinks/OpenFormButton';
import CoverageLabel from '../CoverageLabel';

const styles = () => ({
    genericPaper,
    descriptionListItem,
    relationsHeader,
    relationsTitle,
    linkIconsContainer: {
        display: 'flex',
    },
    infoContainer: {
        display: 'flex',
        justifyContent: 'space-between',
    },
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
            addMessage(`${formResult.entity} was successfully ${formResult.action}`, 'success');
        } else {
            addMessage('Something went wrong', 'error');
        }
        resetFormResult();
    }, [formResult]);

    return (
        <Paper className={classes.genericPaper}>
            {repository && (
                <Fragment>
                    <RepositoryHeader repository={repository} />
                    <div className={classes.descriptionListItem}>
                        {repository.description}
                    </div>
                    <div className={classes.infoContainer}>
                        <div className={classes.linkIconsContainer}>
                            <RepositoryLinks repositoryId={repositoryId} size="big" />
                            {adminMode && (
                                <AddLinkButton
                                    repositoryId={repositoryId}
                                    repositoryName={repository.name}
                                />
                            )}
                        </div>
                        {repository.coverage && (<CoverageLabel value={repository.coverage} big />)}
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
        formResult: state.notifications.formResult,
    }),
    {
        fetchRepository,
        resetFormResult,
        addMessage,
    },
)(withStyles(styles)(Repository));
