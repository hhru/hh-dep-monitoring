import React from 'react';
import PropTypes from 'prop-types';

import Validator from 'Utils/Validator';

const Form = ({ onSubmit, onErrors, validation, children, ...rest }) => {
    const handleSubmit = (event) => {
        event.preventDefault();
        const { fields, rules } = validation;
        const { valid, attributes } = Validator.validateForm(fields, rules);
        if (valid) {
            onSubmit && onSubmit();
            return;
        }
        onErrors && onErrors(attributes);
    };
    return <form onSubmit={handleSubmit} {...rest}>{children}</form>;
};

Form.propTypes = {
    onSubmit: PropTypes.func.isRequired,
    onErrors: PropTypes.func,
    validation: PropTypes.object,
    children: PropTypes.node.isRequired,
};

Form.defaultProps = {
    validation: { fields: {}, rules: {} },
};

export default Form;
