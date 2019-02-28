module.exports = {
    "extends": "airbnb",
    "settings": {
        "react": {
            "version": "detect",
        },
    },
    "rules": {
        "react/jsx-filename-extension": [1, { "extensions": [".js", ".jsx"] }],
    },
    "env":{
        "browser":true,
        "node":true,
    }
};
