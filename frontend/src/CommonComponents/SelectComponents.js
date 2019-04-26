import React from 'react';
import PropTypes from 'prop-types';

import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';

function NoOptionsMessage({ selectProps, innerProps, children }) {
    return (
        <Typography
            color="textSecondary"
            className={selectProps.classes.noOptionsMessage}
            {...innerProps}
        >
            {children}
        </Typography>
    );
}

function inputComponent({ inputRef, ...props }) {
    return <div ref={inputRef} {...props} />;
}

function Control({ selectProps, innerProps, children }) {
    return (
        <TextField
            fullWidth
            InputProps={{
                inputComponent,
                inputProps: {
                    className: selectProps.classes.input,
                    children,
                    ...innerProps,
                },
            }}
            {...selectProps.textFieldProps}
        />
    );
}

function Option({ innerRef, isFocused, innerProps, children }) {
    return (
        <MenuItem
            buttonRef={innerRef}
            selected={isFocused}
            component="div"
            {...innerProps}
        >
            {children}
        </MenuItem>
    );
}

function ValueContainer({ selectProps, children }) {
    return (
        <div className={selectProps.classes.valueContainer}>
            {children}
        </div>
    );
}

NoOptionsMessage.propTypes = {
    selectProps: PropTypes.object.isRequired,
    innerProps: PropTypes.object,
    children: PropTypes.node.isRequired,
};

NoOptionsMessage.defaultProps = {
    innerProps: {},
};

inputComponent.propTypes = {
    inputRef: PropTypes.func.isRequired,
};

Control.propTypes = {
    selectProps: PropTypes.object.isRequired,
    innerProps: PropTypes.object.isRequired,
    children: PropTypes.array.isRequired,
};

Option.propTypes = {
    innerRef: PropTypes.func,
    isFocused: PropTypes.bool.isRequired,
    innerProps: PropTypes.object.isRequired,
    children: PropTypes.node.isRequired,
};

ValueContainer.propTypes = {
    selectProps: PropTypes.object.isRequired,
    children: PropTypes.node.isRequired,
};

export default {
    Control,
    NoOptionsMessage,
    Option,
    ValueContainer,
};
