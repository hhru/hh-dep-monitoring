import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

import { withStyles } from '@material-ui/core/styles';
import Divider from '@material-ui/core/Divider';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';

import { fetchRelations } from 'redux/models/Relation/relationsActions';
import { getRelations } from 'redux/models/Relation/relationsSelectors';
import { noResultMsg } from 'Utils/commonStyles';
import RelationItem from './RelationItem';

const styles = () => ({
    noResultMsg,
});

function Relations({ classes, repositoryId, relations, fetchRelations }) {
    useEffect(() => {
        fetchRelations(repositoryId);
    }, []);

    return relations.length === 0
        ? (
            <Typography variant="body2" className={classes.noResultMsg}>
                Repository has no outgoing relations
            </Typography>
        )
        : (
            <List>
                {relations.map(relation => (
                    <Fragment key={relation.relationId}>
                        <Divider />
                        <RelationItem relation={relation} />
                    </Fragment>
                ))}
            </List>
        );
}

Relations.propTypes = {
    classes: PropTypes.object.isRequired,
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
    (state, ownProps) => ({ relations: getRelations(state, ownProps.repositoryId) }),
    { fetchRelations },
)(withStyles(styles)(Relations));
