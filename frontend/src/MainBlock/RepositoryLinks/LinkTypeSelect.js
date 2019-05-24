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

function LinkTypeSelect({ classes, type, setType, linkTypes }) {
    const handleChange = (event) => {
        setType({ ...type, value: event.target.value });
    };

    return (
        <FormControl className={classes.formControl}>
            <InputLabel>Link type</InputLabel>
            <Select value={type.value} onChange={handleChange}>
                {linkTypes && linkTypes.map(item => (
                    <MenuItem key={item} value={item}>
                        <span className={classes.capitalize}>{item}</span>
                    </MenuItem>
                ))}
            </Select>
            {type.error && (
                <FormHelperText className={classes.errorText}>
                    {type.error}
                </FormHelperText>
            )}
        </FormControl>
    );
}

LinkTypeSelect.propTypes = {
    classes: PropTypes.object.isRequired,
    type: PropTypes.object.isRequired,
    setType: PropTypes.func.isRequired,
    linkTypes: PropTypes.array,
};

export default connect(
    state => ({ linkTypes: state.repositories.linkTypes }),
)(withStyles(styles)(LinkTypeSelect));
