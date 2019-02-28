import React from 'react';
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';

const styles = {
  root: {
    flexGrow: 1,
  },
};

const navbarItems = [
  'Feed',
  'Search',
  'Repositories',
  'Ratings',
  'Users',
  'Troubles',
  'About',
];

function Navbar(props) {
  const { classes } = props;
  return (
    <div className={classes.root}>
      {navbarItems.map(item => (
        <Button key={item} color="inherit">{item}</Button>
      ))}
    </div>
  );
}

Navbar.propTypes = {
  classes: PropTypes.string.isRequired,
};

export default withStyles(styles)(Navbar);
