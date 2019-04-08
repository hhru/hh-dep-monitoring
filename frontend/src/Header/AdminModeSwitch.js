import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Switch from '@material-ui/core/Switch';
import Settings from '@material-ui/icons/Settings';

import { setAdminMode } from 'redux/models/Tools/toolsActions';

function AdminModeSwitch({ adminMode, setAdminMode }) {
    const toggle = () => {
        setAdminMode(!adminMode);
    };

    return (
        <Fragment>
            <Switch onChange={toggle} color="default" />
            <Settings />
        </Fragment>
    );
}

AdminModeSwitch.propTypes = {
    adminMode: PropTypes.bool.isRequired,
    setAdminMode: PropTypes.func.isRequired,
};

export default connect(
    state => ({ adminMode: state.tools.adminMode }),
    { setAdminMode },
)(AdminModeSwitch);
