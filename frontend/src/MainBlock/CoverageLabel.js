import React from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import Chip from '@material-ui/core/Chip';
import Tooltip from '@material-ui/core/Tooltip';

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
        <Tooltip title="Code coverage" placement="left">
            <Chip
                label={`${value}%`}
                className={big ? classes.coverageMark : classes.coverageBigMark}
            />
        </Tooltip>
    );
}

CoverageLabel.propTypes = {
    classes: PropTypes.object.isRequired,
    value: PropTypes.string.isRequired,
    big: PropTypes.bool,
};

export default withStyles(styles)(CoverageLabel);
