import React, { Fragment, useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import SwipeableViews from 'react-swipeable-views';

import Paper from '@material-ui/core/Paper';
import { withStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';

import { fetchRepository } from 'redux/models/Repository/repositoriesActions';
import { resetFormResult, addMessage } from 'redux/models/Notification/notificationsActions';
import { genericPaper, description, relationsHeader, relationsTitle, secondaryItemColor } from 'Utils/commonStyles';
import RepositoryLinks from 'MainBlock/RepositoryLinks/RepositoryLinks';
import AddRelationButton from './Relations/RelationForm/OpenFormButton';
import Relations from './Relations/Relations';
import RepositoryHeader from './RepositoryHeader';
import AddLinkButton from '../RepositoryLinks/OpenFormButton';
import Events from './Events';
import CoverageLabel from '../CoverageLabel';

const styles = () => ({
    genericPaper,
    description,
    relationsHeader,
    relationsTitle,
    linkIconsContainer: {
        display: 'flex',
    },
    infoContainer: {
        display: 'flex',
        justifyContent: 'space-between',
    },
    appBar: {
        boxShadow: 'none',
        marginTop: '30px',
    },
    eventsTitle: {
        marginTop: '14px',
    },
    noResultMsg: {
        margin: '10px',
        color: secondaryItemColor,
    },
});

function Repository({ classes, match, adminMode, repository, fetchRepository,
    formResult, resetFormResult, addMessage }) {
    const { repositoryId } = match.params;
    const [tabValue, setTabValue] = useState(0);

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

    function handleTabChange(event, newValue) {
        setTabValue(newValue);
    }

    return (
        <Paper className={classes.genericPaper}>
            {repository && (
                <Fragment>
                    <RepositoryHeader repository={repository} />
                    <div className={classes.description}>
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
                    <AppBar position="static" color="default" className={classes.appBar}>
                        <Tabs
                            value={tabValue}
                            onChange={handleTabChange}
                            indicatorColor="primary"
                            textColor="primary"
                            variant="fullWidth"
                        >
                            <Tab label="Relations" />
                            <Tab label="Events" />
                        </Tabs>
                    </AppBar>
                    <SwipeableViews index={tabValue}>
                        <Fragment>
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
                        <Fragment>
                            <Typography variant="h5" className={classes.eventsTitle}>Events</Typography>
                            <Events repositoryId={repositoryId} />
                        </Fragment>
                    </SwipeableViews>
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
