import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import Badge from '@material-ui/core/Badge';

import { adminBadge } from 'Utils/commonStyles';

const styles = () => ({
    adminBadge,
});

function AdminBadge({ classes, badgeContent, invisible, adminMode, children }) {
    return (
        <Badge
            badgeContent={badgeContent}
            color="primary"
            classes={{ badge: classes.adminBadge }}
            invisible={!adminMode || invisible}
        >
            {children}
        </Badge>
    );
}

AdminBadge.propTypes = {
    classes: PropTypes.object.isRequired,
    badgeContent: PropTypes.node.isRequired,
    invisible: PropTypes.bool,
    adminMode: PropTypes.bool.isRequired,
    children: PropTypes.node.isRequired,
};

AdminBadge.defaultProps = {
    invisible: false,
};

export default connect(
    state => ({ adminMode: state.tools.adminMode }),
)(withStyles(styles)(AdminBadge));
