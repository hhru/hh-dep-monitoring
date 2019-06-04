import React, { Fragment } from 'react';
import PropTypes from 'prop-types';

import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';

import DependencyItem from './DependencyItem';

function DependencyItems({ dependencies, searchParams }) {
    const { pathToId, level, paramName } = searchParams;
    return (
        <List>
            {dependencies.map(dependency => (
                <Fragment key={dependency.dependencyId}>
                    <Divider />
                    <DependencyItem
                        dependency={dependency}
                        searchParams={{
                            ...searchParams,
                            open: pathToId && pathToId[level] === dependency.artifactVersion.artifact[paramName],
                            level: level + 1,
                        }}
                    />
                </Fragment>
            ))}
        </List>
    );
}

DependencyItems.propTypes = {
    dependencies: PropTypes.array,
    searchParams: PropTypes.object,
};

DependencyItems.defaultProps = {
    dependencies: [],
};

export default DependencyItems;
