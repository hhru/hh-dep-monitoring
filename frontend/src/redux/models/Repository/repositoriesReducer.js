import createReducer from 'redux/createReducer';
import { FETCH_REPOSITORIES, FETCH_REPOSITORY } from './repositoriesActions';

export const initialState = {
    list: {},
    repositoryById: {},
};

export const repositoriesReducer = createReducer(initialState, {
    [FETCH_REPOSITORIES]: (state, action) => ({ ...state, list: action.payload }),
    [FETCH_REPOSITORY]: (state, action) => {
        const { repositoryId, repositoryData } = action.payload;
        return {
            ...state,
            repositoryById: {
                ...state.repositoryById,
                [repositoryId]: repositoryData,
            },
        };
    },
});
