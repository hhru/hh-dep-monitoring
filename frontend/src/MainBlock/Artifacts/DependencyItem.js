import React from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';

import ListItemText from '@material-ui/core/ListItemText';
import { withStyles } from '@material-ui/core/styles';

import { listItemWithoutIcon, genericLink } from 'Utils/commonStyles';
import { Link } from 'react-router-dom';
import ArtfactListItem from './ArtifactListItem';

const styles = theme => ({
    listItemWithoutIcon,
    genericLink,
    searchingDep: {
        color: theme.palette.primary.main,
    },
});

function DependencyItem({ classes, dependency, searchParams }) {
    const { open, pathToId, paramName } = searchParams;
    const { artifact } = dependency.artifactVersion;
    return (
        <ArtfactListItem
            nestedItems={dependency.children}
            searchParams={{
                ...searchParams,
                open: (open && pathToId[pathToId.length - 1] !== artifact[paramName]),
            }}
        >
            {hasAnyChildren => (
                <ListItemText
                    className={classNames({ [classes.listItemWithoutIcon]: !hasAnyChildren })}
                    primary={(
                        <Link
                            to={`/artifacts/${artifact.artifactId}/artifactId`}
                            className={classNames(classes.genericLink, { [classes.searchingDep]:
                                pathToId && (pathToId[pathToId.length - 1] === artifact[paramName]) })
                            }
                        >
                            {`${artifact.groupName}:${artifact.artifactName}`}
                        </Link>
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
    searchParams: PropTypes.object,
};

export default withStyles(styles)(DependencyItem);
