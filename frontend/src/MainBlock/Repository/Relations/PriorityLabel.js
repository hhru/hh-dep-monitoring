import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

import Chip from '@material-ui/core/Chip';
import { withStyles } from '@material-ui/core/styles';

import { priorityMark } from 'Utils/commonStyles';

const styles = () => ({
    priorityMark,
    CRITICAL: {
        backgroundColor: 'rgba( 245, 70, 0, 0.7)',
    },
    PARTIAL: {
        backgroundColor: 'rgba( 245, 160, 0, 0.7)',
    },
    OPTIONAL: {
        backgroundColor: 'rgba( 0, 175, 0, 0.7)',
    },
});

function PriorityLabel({ classes, priority }) {
    return (
        <Chip label={priority} className={classNames(classes.priorityMark, classes[priority])} />
    );
}

PriorityLabel.propTypes = {
    classes: PropTypes.object.isRequired,
    priority: PropTypes.string.isRequired,
};

export default withStyles(styles)(PriorityLabel);
