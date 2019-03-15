module.exports = {
    "extends": "airbnb",
    "settings": {
        "react": {
            "version": "detect",
        },
    },
    "rules": {
        "react/jsx-filename-extension": [1, { "extensions": [".js", ".jsx"] }],
        "indent": ["error", 4, { "SwitchCase": 1 }],
        "react/jsx-indent": ["error", 4],
        "react/jsx-indent-props": ["error", 4],
        "no-shadow": "off",
        "react/forbid-prop-types": ['error', {
            forbid: ['any'],
            checkContextTypes: true,
            checkChildContextTypes: true,
        }],
    },
    "env":{
        "browser":true,
        "node":true,
    }
};
