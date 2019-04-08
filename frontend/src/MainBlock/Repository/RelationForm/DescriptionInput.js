import React from 'react';
import PropTypes from 'prop-types';

import TextField from '@material-ui/core/TextField';

function DescriptionInput({ description, setDescription }) {
    const handleChange = (event) => {
        setDescription({ ...description, value: event.target.value });
    };

    return (
        <TextField
            label="Description"
            multiline
            rowsMax="4"
            value={description.value}
            onChange={handleChange}
            margin="normal"
            fullWidth
        />
    );
}

DescriptionInput.propTypes = {
    description: PropTypes.object.isRequired,
    setDescription: PropTypes.func.isRequired,
};

export default DescriptionInput;
