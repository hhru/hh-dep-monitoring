import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import { Link } from 'react-router-dom';

import { makeStyles } from '@material-ui/styles';

import Icon from 'Utils/icons';
import { archiveColored, archiveIcon, repositoryItemTitle, genericLink } from 'Utils/commonStyles';

const useStyles = makeStyles({
    repositoryItemTitle,
    archiveColored,
    genericLink,
});

export default function ListItemTitle({ repositoryId, name, archived }) {
    const classes = useStyles();
    return (
        <Link to={`/repositories/${repositoryId}`} className={classes.genericLink}>
            <span className={classNames(classes.repositoryItemTitle, { [classes.archiveColored]: archived })}>
                {`${name}`}
            </span>
            {archived && (
                <Icon iconName="archive" styles={archiveIcon} />
            )}
        </Link>
    );
}

ListItemTitle.propTypes = {
    repositoryId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    archived: PropTypes.bool.isRequired,
};
