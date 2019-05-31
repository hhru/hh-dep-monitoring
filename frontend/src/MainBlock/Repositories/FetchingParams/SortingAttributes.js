import React, { Fragment, useState } from 'react';
import PropTypes from 'prop-types';

import SortingAttribute from './SortingAttribute';

const availableAttributes = {
    name: {
        label: 'Repository name',
        state: null,
    },
    createdAt: {
        label: 'Creating date',
        state: null,
    },
    updatedAt: {
        label: 'Updating date',
        state: null,
    },
    repositoryType: {
        label: 'Repository type',
        state: null,
    },
};

const availableStates = [null, 'asc', 'desc'];

const getNextState = currentState => availableStates[
    (availableStates.indexOf(currentState) + 1) % availableStates.length
];

function SortingAttributes({ sortedAttributes, setSortedAttributes }) {
    const [sortAttributes, setSortAttributes] = useState(availableAttributes);

    const getAttribute = attributeName => ({
        name: attributeName,
        label: sortAttributes[attributeName].label,
        state: sortAttributes[attributeName].state,
    });

    const rebuildSortOrder = (attributeName, newState) => {
        const attributeSortIndex = sortedAttributes.findIndex(item => item.name === attributeName);
        const newSortOrder = [...sortedAttributes];

        if (newState && attributeSortIndex === -1) {
            newSortOrder.push(getAttribute(attributeName));
        }

        if (attributeSortIndex !== -1) {
            if (newState) {
                newSortOrder[attributeSortIndex] = getAttribute(attributeName);
            } else {
                newSortOrder.splice(attributeSortIndex, 1);
            }
        }

        setSortedAttributes(newSortOrder);
    };

    const handleClick = (attributeName) => {
        if (!sortAttributes[attributeName]) {
            return;
        }
        const newState = getNextState(sortAttributes[attributeName].state);
        const changedAttributes = Object.assign({}, sortAttributes);
        changedAttributes[attributeName].state = newState;
        setSortAttributes(changedAttributes);
        rebuildSortOrder(attributeName, newState);
    };

    return (
        <Fragment>
            {Object.keys(sortAttributes).map(name => (
                <SortingAttribute
                    key={name}
                    name={name}
                    label={sortAttributes[name].label}
                    state={sortAttributes[name].state}
                    handleClick={handleClick}
                />
            ))}
        </Fragment>
    );
}

SortingAttributes.propTypes = {
    sortedAttributes: PropTypes.array.isRequired,
    setSortedAttributes: PropTypes.func.isRequired,
};

export default SortingAttributes;
