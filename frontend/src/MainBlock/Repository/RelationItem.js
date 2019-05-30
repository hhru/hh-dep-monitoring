import React, { Fragment, useState } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import classNames from 'classnames';

import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import { withStyles } from '@material-ui/core/styles';
import Collapse from '@material-ui/core/Collapse';
import AddIcon from '@material-ui/icons/Add';
import RemoveIcon from '@material-ui/icons/Remove';
import IconButton from '@material-ui/core/IconButton';

import { genericLink, nestedBlock, secondaryItemColor, listItemWithoutIcon } from 'Utils/commonStyles';
import PriorityLabel from './PriorityLabel';
import Relations from './Relations';
import EditRelationButton from './RelationForm/OpenFormButton';
import DeleteRelationButton from './DeleteRelation/DeleteRelationButton';

const styles = () => ({
    nestedBlock,
    genericLink,
    listItemWithoutIcon,
    icon: {
        fill: secondaryItemColor,
    },
    hidden: {
        visibility: 'hidden',
    },
});

function RelationItem({ classes, relation, adminMode }) {
    const [open, setOpen] = useState(false);

    const toggle = () => {
        setOpen(relation.repositoryToHasRelations && !open);
    };

    return (
        <Fragment>
            <ListItem>
                {relation.repositoryToHasRelations && (
                    <IconButton onClick={toggle}>
                        {open
                            ? <RemoveIcon className={classes.icon} />
                            : <AddIcon className={classes.icon} />}
                    </IconButton>
                )}
                <ListItemText
                    className={classNames({ [classes.listItemWithoutIcon]: !relation.repositoryToHasRelations })}
                    primary={(
                        <Fragment>
                            <PriorityLabel priority={relation.priority} />
                            <Link to={`/repositories/${relation.repositoryToId}`} className={classes.genericLink}>
                                {relation.repositoryToName}
                            </Link>
                        </Fragment>
                    )}
                    secondary={relation.description}
                />
                {adminMode && (
                    <Fragment>
                        <EditRelationButton
                            relationId={relation.relationId}
                            repositoryId={relation.repositoryFromId}
                        />
                        <DeleteRelationButton
                            relationId={relation.relationId}
                            repositoryId={relation.repositoryFromId}
                        />
                    </Fragment>
                )}
            </ListItem>
            {relation.repositoryToHasRelations && (
                <Collapse in={open} timeout="auto" unmountOnExit>
                    <div className={classes.nestedBlock}>
                        <Relations repositoryId={relation.repositoryToId} />
                    </div>
                </Collapse>
            )}
        </Fragment>
    );
}

RelationItem.propTypes = {
    classes: PropTypes.object.isRequired,
    relation: PropTypes.object.isRequired,
    adminMode: PropTypes.bool.isRequired,
};

export default connect(
    state => ({ adminMode: state.tools.adminMode }),
)(withStyles(styles)(RelationItem));
