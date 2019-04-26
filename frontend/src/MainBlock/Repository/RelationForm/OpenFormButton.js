import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import AddIcon from '@material-ui/icons/Add';
import Dialog from '@material-ui/core/Dialog';
import EditIcon from '@material-ui/icons/Edit';
import DialogTitle from '@material-ui/core/DialogTitle';
import Fab from '@material-ui/core/Fab';
import Tooltip from '@material-ui/core/Tooltip';

import { fetchRepositories } from 'redux/models/Repository/repositoriesActions';
import { fetchPriorityTypes } from 'redux/models/Relation/relationsActions';
import { getRelation } from 'redux/models/Relation/relationsSelectors';
import { addRelationIcon, relationFormPaper, controlIcon } from 'Utils/commonStyles';
import FormContent from './FormContent';

const styles = () => ({
    addRelationIcon,
    controlIcon,
    relationFormPaper,
});

function OpenFormButton({ classes, repositoryId, repositoryName, fetchRepositories,
    fetchPriorityTypes, relation, priorityTypes }) {
    useEffect(() => {
        fetchRepositories();
    }, []);

    useEffect(() => {
        !priorityTypes && fetchPriorityTypes();
    }, [priorityTypes]);

    const [dialogOpen, setDialogOpen] = useState(false);

    const handleClickOpen = () => {
        setDialogOpen(true);
    };

    const handleClose = () => {
        setDialogOpen(false);
    };

    return (
        <Fragment>
            {relation
                ? <EditIcon className={classes.controlIcon} onClick={handleClickOpen} />
                : (
                    <Tooltip title="Add new relation" placement="left" enterDelay={300}>
                        <Fab onClick={handleClickOpen} className={classes.addRelationIcon}>
                            <AddIcon />
                        </Fab>
                    </Tooltip>
                )}
            <Dialog
                open={dialogOpen}
                onClose={handleClose}
                fullWidth
                classes={{ paper: classes.relationFormPaper }}
            >
                <DialogTitle>
                    {relation ? 'Edit' : 'Add new'}
                    {' relation'}
                </DialogTitle>
                <FormContent
                    setOpen={setDialogOpen}
                    repositoryId={repositoryId}
                    repositoryName={repositoryName}
                    relation={relation}
                />
            </Dialog>
        </Fragment>
    );
}

OpenFormButton.propTypes = {
    classes: PropTypes.object.isRequired,
    repositoryId: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number,
    ]).isRequired,
    repositoryName: PropTypes.string,
    fetchRepositories: PropTypes.func.isRequired,
    fetchPriorityTypes: PropTypes.func.isRequired,
    relation: PropTypes.object,
    priorityTypes: PropTypes.array,
};

OpenFormButton.defaultProps = {
    repositoryName: '',
};

export default connect(
    (state, ownProps) => ({
        relation: getRelation(state, ownProps.repositoryId, ownProps.relationId),
        priorityTypes: state.relations.priorityTypes,
    }),
    {
        fetchRepositories,
        fetchPriorityTypes,
    },
)(withStyles(styles)(OpenFormButton));
