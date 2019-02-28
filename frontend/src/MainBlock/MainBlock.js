import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Repositories from './Repositories';

const styles = theme => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    margin: theme.spacing.unit * 2,
  },
});

function MainBlock(props) {
  const { classes } = props;
  return (
    <div className={classes.root}>
      <Repositories />
    </div>
  );
}

MainBlock.propTypes = {
  classes: PropTypes.string.isRequired,
};

export default withStyles(styles)(MainBlock);
