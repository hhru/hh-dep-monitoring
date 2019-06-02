import React, { Fragment } from 'react';
import PropTypes from 'prop-types';

import { makeStyles } from '@material-ui/styles';

import { dateListItem, descriptionListItem } from 'Utils/commonStyles';
import { formatDate } from 'Utils/date';
import RepositoryTypeLabel from 'MainBlock/RepositoryType/RepositoryTypeLabel';

const useStyles = makeStyles({
    dateListItem,
    descriptionListItem,
    repositoryInfo: {
        margin: '3px 0',
    },
});

export default function ListItemInfo({ description, dateUpdate, dateEvent, type }) {
    const classes = useStyles();
    return (
        <Fragment>
            <div className={classes.descriptionListItem}>
                {!!description && description}
            </div>
            <div className={classes.repositoryInfo}>
                <RepositoryTypeLabel type={type} />
                <span className={classes.dateListItem}>
                    {`Updated ${formatDate(dateUpdate)}`}
                </span>
                {dateEvent && (
                    <span className={classes.dateListItem}>
                        {`Last event ${formatDate(dateEvent)}`}
                    </span>
                )}
            </div>
        </Fragment>
    );
}

ListItemInfo.propTypes = {
    description: PropTypes.string.isRequired,
    dateUpdate: PropTypes.string.isRequired,
    dateEvent: PropTypes.string,
    type: PropTypes.string.isRequired,
};
