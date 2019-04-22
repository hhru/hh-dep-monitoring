import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import Divider from '@material-ui/core/Divider';
import List from '@material-ui/core/List';

import { fetchRelations } from 'redux/models/Relation/relationsActions';
import { getRelations } from 'redux/models/Relation/relationsSelectors';
import RelationItem from './RelationItem';

function Relations({ repositoryId, relations, fetchRelations }) {
    useEffect(() => {
        fetchRelations(repositoryId);
    }, []);

    return (
        <Fragment>
            <List>
                {relations && relations.map(relation => (
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
    relations: PropTypes.array,
    fetchRelations: PropTypes.func.isRequired,
};

Relations.defaultProps = {
    relations: [],
};

export default connect(
    (state, ownProps) => ({
        relations: getRelations(state, ownProps.repositoryId),
    }),
    {
        fetchRelations,
    },
)(Relations);
