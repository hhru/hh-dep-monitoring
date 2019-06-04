import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import Chip from '@material-ui/core/Chip';

import { chip } from 'Utils/commonStyles';

const styles = () => ({
    coverageMark: {
        ...chip,
        marginLeft: '-2px',
    },
    coverageBigMark: {
        marginLeft: '12px',
    },
    chipIcon: {
        fontSize: 20,
    },
});


function CoverageLabel({ classes, value, big }) {
    return (
        <Chip
            label={`${value}%`}
            className={big ? classes.coverageMark : classes.coverageBigMark}
        />
    );
}

CoverageLabel.propTypes = {
    classes: PropTypes.object.isRequired,
    value: PropTypes.string.isRequired,
    big: PropTypes.bool,
};

export default withStyles(styles)(CoverageLabel);
