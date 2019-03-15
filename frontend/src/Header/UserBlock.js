import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';

const styles = {
    avatar: {
        margin: 10,
        width: 60,
        height: 60,
        flexShrink: 0,
    },
};

function UserBlock(props) {
    const { classes, account } = props;
    const picLink = `https://avatars.githubusercontent.com/${account.accountName}`;
    return (
        <Fragment>
            <Button color="inherit">{account.accountName}</Button>
            <Avatar src={picLink} className={classes.avatar} />
        </Fragment>
    );
}

UserBlock.propTypes = {
    classes: PropTypes.string.isRequired,
    account: PropTypes.string.isRequired,
};

export default withStyles(styles)(UserBlock);
