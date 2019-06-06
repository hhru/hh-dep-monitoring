import createReducer from 'redux/createReducer';
import { FETCH_ARTIFACTS_TREE, FETCH_ARTIFACTS } from './artifactsActions';

export const initialState = {
    tree: [],
    list: [],
};

export const artifactsReducer = createReducer(initialState, {
    [FETCH_ARTIFACTS_TREE]: (state, action) => ({ ...state, tree: action.payload }),
    [FETCH_ARTIFACTS]: (state, action) => ({ ...state, list: action.payload }),
});
