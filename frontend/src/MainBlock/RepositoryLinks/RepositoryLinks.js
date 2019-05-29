import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import Link from '@material-ui/core/Link';

import { getRepositoryLinks } from 'redux/models/Repository/repositoriesSelectors';
import { repoLinkIcon, repoLinkIconWrapper } from 'Utils/commonStyles';
import Icon from 'Utils/icons';
import AdminBadge from 'CommonComponents/AdminBadge';
import EditFormButton from 'MainBlock/RepositoryLinks/OpenFormButton';

const styles = () => ({
    repoLinkIconWrapper,
});

const sizer = {
    small: 30,
    big: 40,
};

function RepositoryLinks({ classes, repositoryId, size, repositoryLinks }) {
    return (
        <div>
            {repositoryLinks && repositoryLinks.map(item => (
                <IconButton className={classes.repoLinkIconWrapper} key={item.repositoryLinkId}>
                    <AdminBadge
                        badgeContent={<EditFormButton link={item} repositoryId={repositoryId} />}
                        invisible={size === 'small' || item.linkType === 'GITHUB'}
                    >
                        <Link href={item.linkUrl} target="_blank" rel="noopener">
                            <Icon iconName={item.linkType.toLowerCase()} styles={repoLinkIcon(sizer[size])} />
                        </Link>
                    </AdminBadge>
                </IconButton>
            ))}
        </div>
    );
}

RepositoryLinks.propTypes = {
    classes: PropTypes.object.isRequired,
    repositoryId: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number,
    ]).isRequired,
    size: PropTypes.string.isRequired,
    repositoryLinks: PropTypes.array.isRequired,
};

export default withStyles(styles)(connect(
    (state, ownProps) => ({ repositoryLinks: getRepositoryLinks(state, ownProps.repositoryId) }),
)(RepositoryLinks));
