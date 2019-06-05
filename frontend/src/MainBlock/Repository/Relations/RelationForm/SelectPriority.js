import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import Select from '@material-ui/core/Select';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import FormHelperText from '@material-ui/core/FormHelperText';

import { errorText, capitalize } from 'Utils/commonStyles';

const styles = theme => ({
    formControl: {
        width: theme.spacing.unit * 15,
    },
    errorText,
    capitalize,
});

function SelectPriority({ classes, priority, setPriority, priorityTypes }) {
    const handleChange = (event) => {
        setPriority({ ...priority, value: event.target.value });
    };

    return (
        <FormControl className={classes.formControl}>
            <InputLabel>Priority</InputLabel>
            <Select value={priority.value} onChange={handleChange}>
                {priorityTypes && priorityTypes.map(item => (
                    <MenuItem key={item} value={item}>
                        <span className={classes.capitalize}>{item}</span>
                    </MenuItem>
                ))}
            </Select>
            {priority.error
            && <FormHelperText className={classes.errorText}>{priority.error}</FormHelperText>}
        </FormControl>
    );
}

SelectPriority.propTypes = {
    classes: PropTypes.object.isRequired,
    priority: PropTypes.object.isRequired,
    setPriority: PropTypes.func.isRequired,
    priorityTypes: PropTypes.array.isRequired,
};

export default connect(
    state => ({ priorityTypes: state.relations.priorityTypes }),
)(withStyles(styles)(SelectPriority));
