import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { makeStyles } from '@material-ui/styles';
import IconButton from '@material-ui/core/IconButton';

import { getRepositoryLinks } from 'redux/models/Repository/repositoriesSelectors';
import { repoLinkIcon, repoLinkIconWrapper } from 'Utils/commonStyles';
import Icon from 'Utils/icons';

const useStyles = makeStyles({
    repoLinkIconWrapper,
});

function RepositoryLinks({ size, repositoryLinks }) {
    const classes = useStyles(size);
    return (
        <div>
            {repositoryLinks && repositoryLinks.map(item => (
                <IconButton className={classes.repoLinkIconWrapper} key={item.repositoryLinkId}>
                    <a href={item.linkUrl}>
                        <Icon iconName={item.linkType.toLowerCase()} styles={repoLinkIcon(size)} />
                    </a>
                </IconButton>
            ))}
        </div>
    );
}

RepositoryLinks.propTypes = {
    size: PropTypes.number.isRequired,
    repositoryLinks: PropTypes.array.isRequired,
};

export default connect(
    (state, ownProps) => ({
        repositoryLinks: getRepositoryLinks(state, ownProps.repositoryId),
    }),
)(RepositoryLinks);
