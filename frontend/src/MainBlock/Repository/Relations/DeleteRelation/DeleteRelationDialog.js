import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogActions from '@material-ui/core/DialogActions';
import Button from '@material-ui/core/Button';

import Form from 'CommonComponents/Form';
import { deleteRelation } from 'redux/models/Relation/relationsActions';
import { formButton } from 'Utils/commonStyles';

const styles = () => ({
    formButton,
});

function DeleteRelationDialog({ classes, open, setOpen, relationId, repositoryId, deleteRelation }) {
    const handleClose = () => {
        setOpen(false);
    };

    const handleSubmit = () => {
        deleteRelation(relationId, repositoryId);
        handleClose();
    };

    return (
        <Dialog open={open} onClose={handleClose}>
            <Form onSubmit={handleSubmit}>
                <DialogTitle>Are you sure that you want to delete this relation?</DialogTitle>
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
    );
}

DeleteRelationDialog.propTypes = {
    classes: PropTypes.object.isRequired,
    open: PropTypes.bool.isRequired,
    setOpen: PropTypes.func.isRequired,
    relationId: PropTypes.number.isRequired,
    repositoryId: PropTypes.number.isRequired,
    deleteRelation: PropTypes.func.isRequired,
};

export default connect(
    null,
    { deleteRelation },
)(withStyles(styles)(DeleteRelationDialog));
