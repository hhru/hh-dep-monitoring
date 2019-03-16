module.exports = {
    "extends": "airbnb",
    "settings": {
        "react": {
            "version": "detect",
        },
    },
    "rules": {
        "react/jsx-filename-extension": [1, { "extensions": [".js", ".jsx"] }],
        "indent": ["error", 4],
        "react/jsx-indent": ["error", 4],
        "react/jsx-indent-props": ["error", 4],
    },
    "env":{
        "browser":true,
        "node":true,
    },
    "globals": {
        "it": true,
        "expect": true,
    }
};
