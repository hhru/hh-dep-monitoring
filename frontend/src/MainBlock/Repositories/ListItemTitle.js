import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

import { makeStyles } from '@material-ui/styles';

import Icon from 'Utils/icons';
import { archiveColored, archiveIcon, repositoryItemTitle } from 'Utils/commonStyles';

const useStyles = makeStyles({
    repositoryItemTitle,
    archiveColored,
});

export default function ListItemTitle({ name, isArchived }) {
    const classes = useStyles();
    return (
        <Fragment>
            <span className={classNames(classes.repositoryItemTitle, {
                [classes.archiveColored]: isArchived,
            })}
            >
                {`${name}`}
            </span>
            {isArchived && (
                <Icon iconName="archive" styles={archiveIcon} />
            )}
        </Fragment>
    );
}

ListItemTitle.propTypes = {
    name: PropTypes.string.isRequired,
    isArchived: PropTypes.bool.isRequired,
};
