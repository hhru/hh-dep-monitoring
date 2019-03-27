import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';

import { dateFullItem } from 'Utils/commonStyles';
import formatDate from 'Utils/date';

const styles = () => ({
    dateFullItem,
});

function ListItemDate({ classes, created, updated }) {
    return (
        <div>
            <div className={classes.dateFullItem}>
                {`Created ${formatDate(created)}`}
            </div>
            <div className={classes.dateFullItem}>
                {`Updated ${formatDate(updated)}`}
            </div>
        </div>
    );
}

ListItemDate.propTypes = {
    classes: PropTypes.object.isRequired,
    created: PropTypes.string.isRequired,
    updated: PropTypes.string.isRequired,
};

export default withStyles(styles)(ListItemDate);
