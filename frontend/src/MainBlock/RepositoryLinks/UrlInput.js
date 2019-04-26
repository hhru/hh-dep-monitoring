import React, { Fragment } from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import FormHelperText from '@material-ui/core/FormHelperText';

import { errorText } from 'Utils/commonStyles';

const styles = () => ({
    errorText,
});

function UrlInput({ classes, url, setUrl }) {
    const handleChange = (event) => {
        setUrl({ ...url, value: event.target.value });
    };

    return (
        <Fragment>
            <TextField
                label="Url"
                value={url.value}
                onChange={handleChange}
                margin="normal"
                fullWidth
            />
            {url.error && <FormHelperText className={classes.errorText}>{url.error}</FormHelperText>}
        </Fragment>
    );
}

UrlInput.propTypes = {
    classes: PropTypes.object.isRequired,
    url: PropTypes.object.isRequired,
    setUrl: PropTypes.func.isRequired,
};

export default withStyles(styles)(UrlInput);
