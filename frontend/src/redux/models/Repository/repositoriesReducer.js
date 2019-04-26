import createReducer from 'redux/createReducer';
import { FETCH_REPOSITORIES, FETCH_REPOSITORY, FETCH_LINK_TYPES,
    ADD_LINK, EDIT_LINK, DELETE_LINK } from './repositoriesActions';

export const initialState = {
    list: {},
    repositoryById: {},
    linkTypes: undefined,
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
    [ADD_LINK]: (state, action) => {
        const { repositoryId, newLink } = action.payload;
        const newState = Object.assign({}, state);
        newState.repositoryById[repositoryId].linkUrls = [].concat(
            newState.repositoryById[repositoryId].linkUrls || [], newLink,
        );
        return newState;
    },
    [EDIT_LINK]: (state, action) => {
        const { repositoryId, link } = action.payload;
        const newState = Object.assign({}, state);
        newState.repositoryById[repositoryId].linkUrls = newState.repositoryById[repositoryId]
            .linkUrls.map(item => (item.repositoryLinkId === link.repositoryLinkId ? link : item));
        return newState;
    },
    [DELETE_LINK]: (state, action) => {
        const { repositoryId, linkId } = action.payload;
        const newState = Object.assign({}, state);
        newState.repositoryById[repositoryId].linkUrls = newState.repositoryById[repositoryId]
            .linkUrls.filter(item => (item.repositoryLinkId !== linkId));
        return newState;
    },
});
