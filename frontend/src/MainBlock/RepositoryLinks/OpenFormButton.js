import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import EditIcon from '@material-ui/icons/Edit';
import AddIcon from '@material-ui/icons/Add';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import Fab from '@material-ui/core/Fab';
import Tooltip from '@material-ui/core/Tooltip';

import { fetchLinkTypes } from 'redux/models/Repository/repositoriesActions';
import { addLinkIcon, linkFormPaper, linkControlIcon, linkFormHeader } from 'Utils/commonStyles';
import FormContent from './FormContent';
import DeleteLinkButton from './DeleteLinkButton';

const styles = () => ({
    addLinkIcon,
    linkFormPaper,
    linkControlIcon,
    linkFormHeader,
});

function OpenFormButton({ classes, repositoryId, fetchLinkTypes, linkTypes, link }) {
    useEffect(() => {
        !linkTypes && fetchLinkTypes();
    }, []);

    const [dialogOpen, setDialogOpen] = useState(false);

    const handleClickOpen = () => {
        setDialogOpen(true);
    };

    const handleClose = () => {
        setDialogOpen(false);
    };

    return (
        <Fragment>
            {link
                ? <EditIcon className={classes.linkControlIcon} onClick={handleClickOpen} />
                : (
                    <Tooltip title="Add new link" placement="right" enterDelay={300}>
                        <Fab onClick={handleClickOpen} className={classes.addLinkIcon}>
                            <AddIcon />
                        </Fab>
                    </Tooltip>
                )
            }
            <Dialog
                open={dialogOpen}
                onClose={handleClose}
                fullWidth
                classes={{ paper: classes.linkFormPaper }}
            >
                <div className={classes.linkFormHeader}>
                    <DialogTitle>
                        {link ? 'Edit' : 'Add new'}
                        {' link'}
                    </DialogTitle>
                    {link && (
                        <DeleteLinkButton
                            setOpen={setDialogOpen}
                            repositoryId={repositoryId}
                            linkId={link.repositoryLinkId}
                        />
                    )}
                </div>

                <FormContent
                    setOpen={setDialogOpen}
                    repositoryId={repositoryId}
                    link={link}
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
    fetchLinkTypes: PropTypes.func.isRequired,
    linkTypes: PropTypes.array,
    link: PropTypes.object,
};

export default connect(
    state => ({ linkTypes: state.repositories.linkTypes }),
    { fetchLinkTypes },
)(withStyles(styles)(OpenFormButton));
