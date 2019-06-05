import React, { Fragment, useState } from 'react';
import PropTypes from 'prop-types';

import { withStyles } from '@material-ui/core/styles';
import DeleteIcon from '@material-ui/icons/Delete';

import { controlIcon } from 'Utils/commonStyles';
import DeleteRelationDialog from './DeleteRelationDialog';

const styles = () => ({
    controlIcon,
});

function DeleteRelationButton({ classes, relationId, repositoryId }) {
    const [dialogOpen, setDialogOpen] = useState(false);

    const handleClickOpen = () => {
        setDialogOpen(true);
    };

    return (
        <Fragment>
            <DeleteIcon className={classes.controlIcon} onClick={handleClickOpen} />
            {dialogOpen && (
                <DeleteRelationDialog
                    open={dialogOpen}
                    setOpen={setDialogOpen}
                    relationId={relationId}
                    repositoryId={repositoryId}
                />
            )}
        </Fragment>
    );
}

DeleteRelationButton.propTypes = {
    classes: PropTypes.object.isRequired,
    relationId: PropTypes.number.isRequired,
    repositoryId: PropTypes.number.isRequired,
};

export default withStyles(styles)(DeleteRelationButton);
