import React, { Fragment, useState } from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import classNames from 'classnames';

import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import { withStyles } from '@material-ui/core/styles';
import Collapse from '@material-ui/core/Collapse';
import AddIcon from '@material-ui/icons/Add';
import Remove from '@material-ui/icons/Remove';

import { genericLink, nestedBlock, secondaryItemColor } from 'Utils/commonStyles';
import PriorityLabel from './PriorityLabel';
import Relations from './Relations';

const styles = () => ({
    nestedBlock,
    genericLink,
    icon: {
        fill: secondaryItemColor,
    },
    hidden: {
        visibility: 'hidden',
    },
    withoutIcon: {
        marginLeft: '40px',
    },
});

function RelationItem({ classes, relation }) {
    const [open, setOpen] = useState(false);

    const toggle = () => {
        setOpen(relation.hasRelations && !open);
    };

    return (
        <Fragment>
            <ListItem button onClick={toggle}>
                {relation.hasRelations && (open
                    ? <Remove className={classes.icon} /> : <AddIcon className={classes.icon} />)}
                <ListItemText
                    className={classNames({ [classes.withoutIcon]: !relation.hasRelations })}
                    primary={(
                        <Fragment>
                            <PriorityLabel priority={relation.priority} />
                            <Link to={`/Repositories/${relation.repositoryToId}`} className={classes.genericLink}>
                                {relation.repositoryToName}
                            </Link>
                        </Fragment>
                    )}
                    secondary={relation.description}
                />
            </ListItem>
            {relation.hasRelations && (
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
};

export default withStyles(styles)(RelationItem);
