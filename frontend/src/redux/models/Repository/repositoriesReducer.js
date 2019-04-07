import { REPOSITORIES_DATA, REPOSITORY_DATA_BY_ID } from './repositoriesActions';
import createReducer from '../../createReducer';

export const initialState = {
    list: {},
    repositoryById: {},
};

export const repositoriesReducer = createReducer(initialState, {
    [REPOSITORIES_DATA]: (state, action) => ({ ...state, list: action.payload }),
    [REPOSITORY_DATA_BY_ID]: (state, action) => {
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
