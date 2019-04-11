import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

import ListItemInfo from './ListItemInfo';
import ListItemTitle from './ListItemTitle';

export default function RepositoryItem({ repository }) {
    return (
        <ListItem button component={Link} to={`/Repositories/${repository.repositoryId}`}>
            <ListItemText
                primary={
                    <ListItemTitle name={repository.name} archived={repository.archived} />}
                secondary={
                    <ListItemInfo descr={repository.description} date={repository.updatedAt} />}
            />
        </ListItem>
    );
}

RepositoryItem.propTypes = {
    repository: PropTypes.object.isRequired,
};
