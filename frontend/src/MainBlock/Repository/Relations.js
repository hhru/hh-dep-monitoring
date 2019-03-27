import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Divider from '@material-ui/core/Divider';
import List from '@material-ui/core/List';

import { fetchRelationsByRepoId } from 'redux/models/Relation/relationsActions';
import RelationItem from './RelationItem';

function Relations({ repositoryId, relations, fetchRelationsByRepoId }) {
    useEffect(() => {
        fetchRelationsByRepoId(repositoryId);
    }, []);

    return (
        <Fragment>
            <List>
                {relations.items && relations.items.map(relation => (
                    <Fragment key={relation.relationId}>
                        <Divider />
                        <RelationItem relation={relation} />
                    </Fragment>
                ))}
            </List>
        </Fragment>
    );
}

Relations.propTypes = {
    repositoryId: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number,
    ]).isRequired,
    relations: PropTypes.object,
    fetchRelationsByRepoId: PropTypes.func.isRequired,
};

Relations.defaultProps = {
    relations: {},
};

export default connect(
    (state, ownProps) => ({ relations: state.relations.relationsByRepoId[ownProps.repositoryId] }),
    { fetchRelationsByRepoId },
)(Relations);
