import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

import ListItemText from '@material-ui/core/ListItemText';
import { withStyles } from '@material-ui/core/styles';

import { listItemWithoutIcon } from 'Utils/commonStyles';
import ArtfactListItem from './ArtifactListItem';

const styles = () => ({
    listItemWithoutIcon,
});

function DependencyItem({ classes, dependency }) {
    return (
        <ArtfactListItem nestedItems={dependency.children}>
            {hasAnyChildren => (
                <ListItemText
                    className={classNames({ [classes.listItemWithoutIcon]: !hasAnyChildren })}
                    primary={(
                        <Fragment>
                            {dependency.artifactVersion.artifact.groupName}
                            :
                            {dependency.artifactVersion.artifact.artifactName}
                        </Fragment>
                    )}
                    secondary={`ver. ${dependency.artifactVersion.version}`}
                />
            )}
        </ArtfactListItem>
    );
}

DependencyItem.propTypes = {
    classes: PropTypes.object.isRequired,
    dependency: PropTypes.object.isRequired,
};

export default withStyles(styles)(DependencyItem);
