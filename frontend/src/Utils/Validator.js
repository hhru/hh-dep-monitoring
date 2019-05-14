const CoreValidators = {
    required(value) {
        return !(
            value === undefined
            || value === ''
            || value === 0
            || value === '0'
            || value === null
            || value === false
            || (value.length && value.length === 0)
        );
    },
    url(value) {
        const regURL = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-/]))?/;
        return regURL.test(value);
    },
};

const Validator = {
    generateAttribute(value, rest = {}) {
        return {
            ...rest,
            value,
            error: undefined,
        };
    },

    validate(rule, value) {
        return CoreValidators[rule] ? CoreValidators[rule](value) : false;
    },
    validateAttribute(name, value, rules) {
        const attributeResult = {
            value,
            error: undefined,
        };
        rules[name] && rules[name].some((rule) => {
            if (!Validator.validate(rule.rule, value)) {
                attributeResult.error = rule.message || `Invalid value of field ${name}`;
                return true;
            }
            return false;
        });
        return attributeResult;
    },
    validateForm(attributes, rules) {
        const validatedAttributes = {};
        let formValid = true;

        Object.keys(attributes).forEach((name) => {
            validatedAttributes[name] = this.validateAttribute(name, attributes[name].value, rules);
            if (validatedAttributes[name].error) {
                formValid = false;
            }
        });

        return {
            valid: formValid,
            attributes: validatedAttributes,
        };
    },
    getFormData(attributes) {
        const formData = {};
        Object.keys(attributes).forEach((attributeName) => {
            formData[attributeName] = attributes[attributeName].value;
        });

        return formData;
    },
};

export default Validator;
