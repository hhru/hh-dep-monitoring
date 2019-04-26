export const ADD_MESSAGE = 'ADD_MESSAGE';
export const REMOVE_MESSAGE = 'REMOVE_MESSAGE';
export const SET_FORM_RESULT = 'SET_FORM_RESULT';
export const RESET_FORM_RESULT = 'RESET_FORM_RESULT';

export const addMessageAction = (message, variant) => ({
    type: ADD_MESSAGE,
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
    type: REMOVE_MESSAGE,
    payload: {
        key,
    },
});

export const setFormResultAction = (result, entity, action) => ({
    type: SET_FORM_RESULT,
    payload: {
        saveResult: result,
        entity,
        actionType: action,
    },
});

export const resetFormResultAction = () => ({
    type: RESET_FORM_RESULT,
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

export function resetFormResult() {
    return (dispatch) => {
        dispatch(resetFormResultAction());
    };
}
