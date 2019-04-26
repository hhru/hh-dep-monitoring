import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import Link from '@material-ui/core/Link';

import { getRepositoryLinks } from 'redux/models/Repository/repositoriesSelectors';
import { repoLinkIcon, repoLinkIconWrapper, openFormButton } from 'Utils/commonStyles';
import Icon from 'Utils/icons';
import EditFormButton from 'MainBlock/RepositoryLinks/OpenFormButton';

const styles = () => ({
    openFormButton,
    repoLinkIconWrapper,
});

const sizer = {
    small: 30,
    big: 40,
};

function RepositoryLinks({ classes, repositoryId, size, repositoryLinks, adminMode }) {
    return (
        <div>
            {repositoryLinks && repositoryLinks.map(item => (
                <IconButton className={classes.repoLinkIconWrapper} key={item.repositoryLinkId}>
                    <Badge
                        badgeContent={
                            <EditFormButton link={item} repositoryId={repositoryId} />
                        }
                        color="primary"
                        classes={{ badge: classes.openFormButton }}
                        invisible={!adminMode || size === 'small' || item.linkType === 'GITHUB'}
                    >
                        <Link href={item.linkUrl} target="_blank" rel="noopener">
                            <Icon iconName={item.linkType.toLowerCase()} styles={repoLinkIcon(sizer[size])} />
                        </Link>
                    </Badge>
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
    adminMode: PropTypes.bool.isRequired,
};

export default withStyles(styles)(connect(
    (state, ownProps) => ({
        repositoryLinks: getRepositoryLinks(state, ownProps.repositoryId),
        adminMode: state.tools.adminMode,
    }),
)(RepositoryLinks));
