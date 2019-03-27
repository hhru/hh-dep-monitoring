module.exports = {
    "extends": "airbnb",
    "settings": {
        "react": {
            "version": "detect",
        },
        "import/resolver": {
            "node": {
                "moduleDirectory": [
                    "node_modules",
                    "src/"
                ]
            }
        }
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
        "no-underscore-dangle": ["error", { "allow": ["__REDUX_DEVTOOLS_EXTENSION_COMPOSE__"] }],
        "no-unused-expressions": [2, { allowTernary: true }],
        "object-curly-newline": [
            "error", {
                "ObjectPattern": {"multiline": false}
            }
        ],
        "import/no-cycle": "off",
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
