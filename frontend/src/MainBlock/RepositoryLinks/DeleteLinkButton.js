import React, { Fragment, useState } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import DeleteIcon from '@material-ui/icons/Delete';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogActions from '@material-ui/core/DialogActions';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';

import Form from 'CommonComponents/Form';
import { controlIcon, formButton } from 'Utils/commonStyles';
import { deleteLink } from 'redux/models/Repository/repositoriesActions';

const styles = () => ({
    controlIcon,
    formButton,
});

function DeleteRelationButton({ classes, repositoryId, linkId, setOpen, deleteLink }) {
    const [dialogOpen, setDialogOpen] = useState(false);

    const handleClickOpen = () => {
        setDialogOpen(true);
    };

    const handleClose = () => {
        setDialogOpen(false);
    };

    const handleSubmit = () => {
        deleteLink(linkId, repositoryId);
        handleClose();
        setOpen(false);
    };

    return (
        <Fragment>
            <DeleteIcon className={classes.controlIcon} onClick={handleClickOpen} />
            <Dialog open={dialogOpen} onClose={handleClose}>
                <Form onSubmit={handleSubmit}>
                    <DialogTitle>Are you sure that you want to delete this link?</DialogTitle>
                    <DialogActions>
                        <Button onClick={handleClose} className={classes.formButton}>
                            No
                        </Button>
                        <Button type="submit" variant="contained" color="primary">
                            Yes
                        </Button>
                    </DialogActions>
                </Form>
            </Dialog>
        </Fragment>
    );
}

DeleteRelationButton.propTypes = {
    classes: PropTypes.object.isRequired,
    repositoryId: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number,
    ]).isRequired,
    linkId: PropTypes.number.isRequired,
    setOpen: PropTypes.func.isRequired,
    deleteLink: PropTypes.func.isRequired,
};

export default connect(
    null,
    { deleteLink },
)(withStyles(styles)(DeleteRelationButton));
