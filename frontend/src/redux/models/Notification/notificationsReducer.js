import createReducer from 'redux/createReducer';
import { ADD_MESSAGE, REMOVE_MESSAGE, SET_FORM_RESULT, RESET_FORM_RESULT } from './notificationsActions';

export const initialState = {
    messages: [],
    formResult: undefined,
};

export const notificationsReducer = createReducer(initialState, {
    [ADD_MESSAGE]: (state, action) => {
        const { notification } = action.payload;
        return {
            ...state,
            messages: [
                ...state.messages,
                notification,
            ],
        };
    },
    [REMOVE_MESSAGE]: (state, action) => {
        const { key } = action.payload;
        return {
            ...state,
            messages: state.messages.filter(notification => notification.key !== key),
        };
    },
    [SET_FORM_RESULT]: (state, action) => {
        const { saveResult, entity, actionType } = action.payload;
        return {
            ...state,
            formResult: {
                success: saveResult,
                entity,
                action: actionType,
            },
        };
    },
    [RESET_FORM_RESULT]: state => ({
        ...state,
        formResult: undefined,
    }),
});
