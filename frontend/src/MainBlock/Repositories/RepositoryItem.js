import React from 'react';
import PropTypes from 'prop-types';

import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

import ListItemInfo from './ListItemInfo';
import ListItemTitle from './ListItemTitle';

function ListItemLink(props) {
    return <ListItem button component="a" {...props} />;
}

export default function RepositoryItem({ repository }) {
    return (
        <ListItemLink href={repository.htmlUrl}>
            <ListItemText
                primary={
                    <ListItemTitle name={repository.name} isArchived={repository.isArchived} />}
                secondary={
                    <ListItemInfo descr={repository.description} date={repository.updatedAt} />}
            />
        </ListItemLink>
    );
}

RepositoryItem.propTypes = {
    repository: PropTypes.object.isRequired,
};
