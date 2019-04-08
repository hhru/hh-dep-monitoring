import createReducer from 'redux/createReducer';
import { ADD_MESSAGE, REMOVE_MESSAGE } from './notificationsActions';

export const initialState = [];

export const notificationsReducer = createReducer(initialState, {
    [ADD_MESSAGE]: (state, action) => {
        const { notification } = action.payload;
        return [
            ...state,
            notification,
        ];
    },
    [REMOVE_MESSAGE]: (state, action) => {
        const { key } = action.payload;
        return state.filter(notification => notification.key !== key);
    },
});
