import createReducer from 'redux/createReducer';
import { FETCH_REPOSITORIES, FETCH_REPOSITORY, FETCH_LINK_TYPES, FETCH_REPOSITORY_LINKS } from './repositoriesActions';

export const initialState = {
    list: {},
    repositoryById: {},
    linkTypes: {},
    links: {},
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
    [FETCH_LINK_TYPES]: (state, action) => {
        const { linkTypes } = action.payload;
        return {
            ...state,
            linkTypes,
        };
    },
    [FETCH_REPOSITORY_LINKS]: (state, action) => {
        const { repositoryId, links } = action.payload;
        return {
            ...state,
            links: {
                ...state.links,
                [repositoryId]: links,
            },
        };
    },
});
