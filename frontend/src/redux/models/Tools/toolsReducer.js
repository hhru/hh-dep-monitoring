import createReducer from 'redux/createReducer';
import { SET_ADMIN_MODE } from './toolsActions';

export const initialState = {
    adminMode: false,
};

export const toolsReducer = createReducer(initialState, {
    [SET_ADMIN_MODE]: (state, action) => {
        const { option } = action.payload;
        return {
            ...state,
            adminMode: option,
        };
    },
});
