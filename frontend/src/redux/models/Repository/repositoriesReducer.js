import createReducer from 'redux/createReducer';
import { FETCH_REPOSITORIES, FETCH_REPOSITORIES_PAGE, CLEAR_REPOSITORIES_PAGES, FETCH_REPOSITORY, EDIT_REPOSITORY,
    FETCH_REPOSITORY_TYPES, FETCH_LINK_TYPES, ADD_LINK, EDIT_LINK, DELETE_LINK, SET_SEARCH_STRING } from './repositoriesActions';

export const initialState = {
    list: {},
    pages: {},
    pageCount: undefined,
    searchString: '',
    repositoryById: {},
    repositoryTypes: undefined,
    linkTypes: undefined,
};

export const repositoriesReducer = createReducer(initialState, {
    [FETCH_REPOSITORIES]: (state, action) => ({ ...state, list: action.payload }),
    [FETCH_REPOSITORIES_PAGE]: (state, action) => {
        const { page, repositories } = action.payload;
        return {
            ...state,
            pageCount: repositories.pages,
            pages: {
                ...state.pages,
                [page]: repositories,
            },
        };
    },
    [CLEAR_REPOSITORIES_PAGES]: state => ({ ...state, pages: {}, pageCount: undefined }),
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
    [EDIT_REPOSITORY]: (state, action) => {
        const { repositoryId, repositoryData } = action.payload;
        const newState = Object.assign({}, state);
        newState.repositoryById[repositoryId] = repositoryData;
        Object.keys(newState.pages).some((page) => {
            let result = false;
            newState.pages[page].items = newState.pages[page].items
                .map((item) => {
                    if (item.repositoryId === repositoryId) {
                        result = true;
                        return repositoryData;
                    }
                    return item;
                });
            return result;
        });
        return newState;
    },
    [FETCH_REPOSITORY_TYPES]: (state, action) => {
        const { repositoryTypes } = action.payload;
        return {
            ...state,
            repositoryTypes,
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
    [SET_SEARCH_STRING]: (state, action) => ({ ...state, searchString: action.payload }),
});
