import createReducer from 'redux/createReducer';
import { FETCH_ARTIFACTS } from './artifactsActions';

export const initialState = {
    tree: [],
};

export const artifactsReducer = createReducer(initialState, {
    [FETCH_ARTIFACTS]: (state, action) => ({ ...state, tree: action.payload }),
});
