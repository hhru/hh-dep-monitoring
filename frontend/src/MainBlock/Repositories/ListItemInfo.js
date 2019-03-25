import React, { Fragment } from 'react';
import PropTypes from 'prop-types';

import { makeStyles } from '@material-ui/styles';

import { dateListItem } from '../../Utils/commonStyles';
import formatDate from '../../Utils/date';

const useStyles = makeStyles({
    dateListItem,
});

export default function ListItemInfo({ descr, date }) {
    const classes = useStyles();
    return (
        <Fragment>
            {`${descr}`}
            <div className={classes.dateListItem}>
                {`Updated ${formatDate(date)}`}
            </div>
        </Fragment>
    );
}

ListItemInfo.propTypes = {
    descr: PropTypes.string.isRequired,
    date: PropTypes.string.isRequired,
};
