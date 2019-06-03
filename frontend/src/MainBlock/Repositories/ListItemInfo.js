import React, { Fragment } from 'react';
import PropTypes from 'prop-types';

import { makeStyles } from '@material-ui/styles';

import { dateListItem } from 'Utils/commonStyles';
import formatDate from 'Utils/date';

const useStyles = makeStyles({
    dateListItem,
});

export default function ListItemInfo({ descr, dateUpdate, dateEvent }) {
    const classes = useStyles();
    return (
        <Fragment>
            {`${descr}`}
            <span className={classes.dateListItem}>
                {`Updated ${formatDate(dateUpdate)}`}
            </span>
            {dateEvent && (
                <span className={classes.dateListItem}>
                    {`Last event ${formatDate(dateEvent)}`}
                </span>
            )}
        </Fragment>
    );
}

ListItemInfo.propTypes = {
    descr: PropTypes.string.isRequired,
    dateUpdate: PropTypes.string.isRequired,
    dateEvent: PropTypes.string,
};
