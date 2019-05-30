import React, { Fragment } from 'react';
import PropTypes from 'prop-types';

import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';

import DependencyItem from './DependencyItem';

function DependencyItems({ dependencies }) {
    return (
        <List>
            {dependencies.map(dependency => (
                <Fragment key={dependency.dependencyId}>
                    <Divider />
                    <DependencyItem dependency={dependency} />
                </Fragment>
            ))}
        </List>
    );
}

DependencyItems.propTypes = {
    dependencies: PropTypes.array,
};

DependencyItems.defaultProps = {
    dependencies: [],
};

export default DependencyItems;
