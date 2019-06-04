import React, { Fragment, useEffect, useState } from 'react';
import PropTypes from 'prop-types';

import ListItem from '@material-ui/core/ListItem';
import IconButton from '@material-ui/core/IconButton';
import AddIcon from '@material-ui/icons/Add';
import RemoveIcon from '@material-ui/icons/Remove';
import Collapse from '@material-ui/core/Collapse';
import { withStyles } from '@material-ui/core';

import { genericLink, nestedBlock, secondaryItemColor, listItem } from 'Utils/commonStyles';
import DependencyItems from './DependencyItems';

const styles = () => ({
    nestedBlock,
    genericLink,
    icon: {
        fill: secondaryItemColor,
    },
    listItem,
});

function ArtfactListItem({ classes, children, nestedItems, searchParams }) {
    const [open, setOpen] = useState(!!searchParams.open);

    useEffect(() => {
        setOpen(searchParams.open);
    }, [searchParams.open]);

    const hasAnyChildren = Boolean(nestedItems && nestedItems.length);

    const toggle = () => {
        setOpen(hasAnyChildren && !open);
    };

    return (
        <Fragment>
            <ListItem className={classes.listItem}>
                {hasAnyChildren && (
                    <IconButton onClick={toggle}>
                        {open
                            ? <RemoveIcon className={classes.icon} />
                            : <AddIcon className={classes.icon} />}
                    </IconButton>
                )}
                {children(hasAnyChildren)}
            </ListItem>
            {hasAnyChildren && (
                <Collapse in={open} timeout="auto" unmountOnExit>
                    <div className={classes.nestedBlock}>
                        <DependencyItems dependencies={nestedItems} searchParams={searchParams} />
                    </div>
                </Collapse>
            )}
        </Fragment>
    );
}

ArtfactListItem.propTypes = {
    classes: PropTypes.object.isRequired,
    children: PropTypes.func.isRequired,
    nestedItems: PropTypes.array,
    searchParams: PropTypes.object,
};

ArtfactListItem.defaultProps = {
    nestedItems: [],
};

export default withStyles(styles)(ArtfactListItem);
