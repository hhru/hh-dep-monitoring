import React from 'react';
import PropTypes from 'prop-types';

export const icons = {
    archive: styles => (
        <svg style={styles} xmlns="http://www.w3.org/2000/svg" viewBox="0 0 14 14">
            <path d="M 4.5 2 C 3.6774686 2 3 2.6774686 3 3.5 L 3 12.5 C 3 13.322531 3.6774686 14 4.5 14 L 11.5 14 C 12.322531 14 13 13.322531 13 12.5 L 13 5.2929688 L 12.853516 5.1464844 L 9.7070312 2 L 4.5 2 z M 4.5 3 L 7 3 L 7 3.5 L 7 4 L 8 4 L 8 3.5 L 8 3 L 9.2929688 3 L 12 5.7070312 L 12 12.5 C 12 12.781469 11.781469 13 11.5 13 L 4.5 13 C 4.2185314 13 4 12.781469 4 12.5 L 4 3.5 C 4 3.2185314 4.2185314 3 4.5 3 z M 8 4 L 8 5 L 9 5 L 9 4 L 8 4 z M 8 5 L 7 5 L 7 6 L 8 6 L 8 5 z M 8 6 L 8 7 L 9 7 L 9 6 L 8 6 z M 8 7 L 7 7 L 7 8 L 7 10 C 7 10.552 7.448 11 8 11 C 8.552 11 9 10.552 9 10 L 9 8 L 8 8 L 8 7 z" />
        </svg>
    ),
};

export default function Icon({ iconName, styles }) {
    return icons[iconName](styles);
}

Icon.propTypes = {
    iconName: PropTypes.string.isRequired,
    styles: PropTypes.object.isRequired,
};
