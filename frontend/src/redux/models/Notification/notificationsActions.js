export const ADD_MESSAGE = 'ADD_MESSAGE';
export const REMOVE_MESSAGE = 'REMOVE_MESSAGE';

export const addMessageAction = (message, variant) => ({
    type: 'ADD_MESSAGE',
    payload: {
        notification: {
            key: new Date().getTime() + Math.random(),
            message,
            options: {
                variant,
            },
        },
    },
});

export const removeMessageAction = key => ({
    type: 'REMOVE_MESSAGE',
    payload: {
        key,
    },
});

export function addMessage(message, variant) {
    return (dispatch) => {
        dispatch(addMessageAction(message, variant));
    };
}

export function removeMessage(key) {
    return (dispatch) => {
        dispatch(removeMessageAction(key));
    };
}
