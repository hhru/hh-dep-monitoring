import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import classNames from 'classnames';

import { withStyles } from '@material-ui/core/styles';
import Divider from '@material-ui/core/Divider';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Chip from '@material-ui/core/Chip';
import ListItem from '@material-ui/core/ListItem';

import { fetchEvents } from 'redux/models/Event/eventsActions';
import { noResultMsg, chip, errorText, descriptionListItem, flexVerticalContainer } from 'Utils/commonStyles';
import { formatDate, formatTime } from 'Utils/date';

const styles = () => ({
    noResultMsg,
    chip,
    errorText,
    descriptionListItem,
    listItem: {
        ...flexVerticalContainer,
        alignItems: 'flex-start',
    },
});

function Events({ classes, repositoryId, events, fetchEvents }) {
    useEffect(() => {
        fetchEvents(repositoryId);
    }, []);

    return !events.items || events.items.length === 0
        ? (
            <Typography variant="body2" className={classes.noResultMsg}>
                No events for this repository
            </Typography>
        )
        : (
            <List>
                {events.items.map(event => (
                    <Fragment key={event.eventId}>
                        <Divider />
                        <ListItem className={classes.listItem}>
                            <div>
                                {`${event.artifact.groupName}:${event.artifact.artifactName}
                                was updated ${formatDate(event.createdAt)}`}
                                {formatTime(event.createdAt) && (` at ${formatTime(event.createdAt)}`)}
                            </div>
                            <div className={classes.descriptionListItem}>
                                <Chip
                                    label={event.type}
                                    className={classNames(classes.chip,
                                        { [classes.errorText]: event.type === 'CONFLICT' })}
                                />
                                {event.description}
                            </div>
                        </ListItem>
                    </Fragment>
                ))}
                <Divider />
            </List>
        );
}

Events.propTypes = {
    classes: PropTypes.object.isRequired,
    repositoryId: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number,
    ]).isRequired,
    events: PropTypes.object,
    fetchEvents: PropTypes.func.isRequired,
};

Events.defaultProps = {
    events: {},
};

export default connect(
    (state, ownProps) => ({ events: state.events.eventsByRepoId[ownProps.repositoryId] }),
    { fetchEvents },
)(withStyles(styles)(Events));
