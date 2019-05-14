import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';

import Form from 'CommonComponents/Form';
import { addLink, editLink } from 'redux/models/Repository/repositoriesActions';
import Validator from 'Utils/Validator';
import { formButton, dialogContent } from 'Utils/commonStyles';
import LinkTypeSelect from './LinkTypeSelect';
import UrlInput from './UrlInput';

const styles = () => ({
    formButton,
    dialogContent,
});

function FormContent({ classes, setOpen, repositoryId, addLink, editLink, formResult, link }) {
    const isNewLink = !link;

    const values = {
        type: isNewLink ? '' : link.linkType,
        url: isNewLink ? '' : link.linkUrl,
    };

    const [type, setType] = useState(Validator.generateAttribute(values.type));
    const [url, setUrl] = useState(Validator.generateAttribute(values.url));

    const handleClose = () => {
        setOpen(false);
    };

    useEffect(() => {
        formResult.success && handleClose();
    }, [formResult]);

    const validationRules = {
        type: [
            { rule: 'required', message: 'Link type is required to be selected' },
        ],
        url: [
            { rule: 'required', message: 'Link url is required to be filled' },
            { rule: 'url', message: 'Link url must be valid' },
        ],
    };

    const handleSubmit = () => {
        isNewLink
            ? addLink(repositoryId, type.value, url.value)
            : editLink(repositoryId, link.repositoryLinkId, type.value, url.value);
    };

    const showErrors = (attributes) => {
        setType(attributes.type);
        setUrl(attributes.url);
    };

    return (
        <Form
            onSubmit={handleSubmit}
            onErrors={showErrors}
            validation={{ fields: { type, url }, rules: validationRules }}
        >
            <DialogContent classes={{ root: classes.dialogContent }}>
                {isNewLink && (
                    <DialogContentText paragraph>
                        To add new link, please enter following fields.
                    </DialogContentText>
                )}
                <LinkTypeSelect type={type} setType={setType} />
                <UrlInput url={url} setUrl={setUrl} />
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose} className={classes.formButton}>
                    Cancel
                </Button>
                <Button type="submit" variant="contained" color="primary">
                    Submit
                </Button>
            </DialogActions>
        </Form>
    );
}

FormContent.propTypes = {
    classes: PropTypes.object.isRequired,
    setOpen: PropTypes.func.isRequired,
    repositoryId: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
    addLink: PropTypes.func.isRequired,
    editLink: PropTypes.func.isRequired,
    formResult: PropTypes.object,
    link: PropTypes.object,
};

FormContent.defaultProps = {
    formResult: {},
};

export default connect(
    state => ({ formResult: state.notifications.formResult }),
    {
        addLink,
        editLink,
    },
)(withStyles(styles)(FormContent));
