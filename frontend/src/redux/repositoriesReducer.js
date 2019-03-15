import { REPOSITORIES_DATA } from './repositoriesActions';
import createReducer from './createReducer';

export const initialState = {
    repositories: {},
};

export const repositoriesReducer = createReducer(initialState, {
    [REPOSITORIES_DATA]: (state, action) => ({ ...state, repositories: action.payload }),
});
