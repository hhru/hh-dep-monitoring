import axios from 'axios';
import { addMessageAction, setFormResultAction } from 'redux/models/Notification/notificationsActions';
import { REPOSITORY_URL, REPOSITORY_LINK_URL } from 'Utils/constants';

export const FETCH_REPOSITORIES = 'FETCH_REPOSITORIES ';
export const FETCH_REPOSITORY = 'FETCH_REPOSITORY ';
export const FETCH_LINK_TYPES = 'FETCH_LINK_TYPES';
export const ADD_LINK = 'ADD_LINK';
export const EDIT_LINK = 'EDIT_LINK';
export const DELETE_LINK = 'DELETE_LINK';
export const SET_FORM_RESULT = 'SET_FORM_RESULT';

export const fetchRepositoriesAction = repositories => ({
    type: FETCH_REPOSITORIES,
    payload: repositories,
});

export const fetchRepositoryAction = (repositoryId, repositoryData) => ({
    type: FETCH_REPOSITORY,
    payload: {
        repositoryId,
        repositoryData,
    },
});

export const fetchLinkTypesAction = linkTypes => ({
    type: FETCH_LINK_TYPES,
    payload: {
        linkTypes,
    },
});

export const addLinkAction = (repositoryId, newLink) => ({
    type: ADD_LINK,
    payload: {
        repositoryId,
        newLink,
    },
});

export const editLinkAction = (repositoryId, link) => ({
    type: EDIT_LINK,
    payload: {
        repositoryId,
        link,
    },
});

export const deleteLinkAction = (repositoryId, linkId) => ({
    type: DELETE_LINK,
    payload: {
        repositoryId,
        linkId,
    },
});

export function fetchRepositories() {
    return (dispatch) => {
        axios.get(REPOSITORY_URL)
            .then((response) => {
                dispatch(fetchRepositoriesAction({ items: response.data }));
            })
            .catch(() => {
                dispatch(addMessageAction('Error in fetching repositories', 'error'));
            });
    };
}

export function fetchRepository(id) {
    return (dispatch) => {
        axios.get(`${REPOSITORY_URL}/${id}`)
            .then((response) => {
                dispatch(fetchRepositoryAction(id, response.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Error in fetching repository data', 'error'));
            });
    };
}

export function fetchLinkTypes() {
    return (dispatch) => {
        axios.get(`${REPOSITORY_LINK_URL}types`)
            .then((response) => {
                dispatch(fetchLinkTypesAction(response.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Can\'t get link types for relations', 'error'));
            });
    };
}

export function addLink(repositoryId, linkType, linkUrl) {
    return (dispatch) => {
        const newLink = {
            linkType,
            linkUrl,
            repositoryId,
        };
        axios.post(REPOSITORY_LINK_URL, newLink)
            .then((response) => {
                dispatch(addLinkAction(repositoryId, response.data));
                dispatch(setFormResultAction(true, 'Link', 'added'));
            })
            .catch(() => dispatch(setFormResultAction(false)));
    };
}

export function editLink(repositoryId, repositoryLinkId, linkType, linkUrl) {
    return (dispatch) => {
        const editedLink = {
            repositoryLinkId,
            linkType,
            linkUrl,
            repositoryId,
        };
        axios.put(REPOSITORY_LINK_URL + repositoryLinkId, editedLink)
            .then((response) => {
                dispatch(editLinkAction(repositoryId, response.data));
                dispatch(setFormResultAction(true, 'Link', 'edited'));
            })
            .catch(() => dispatch(setFormResultAction(false)));
    };
}

export function deleteLink(repositoryLinkId, repositoryId) {
    return (dispatch) => {
        axios.delete(REPOSITORY_LINK_URL + repositoryLinkId)
            .then(() => {
                dispatch(deleteLinkAction(repositoryId, repositoryLinkId));
                dispatch(setFormResultAction(true, 'Link', 'deleted'));
            })
            .catch(() => dispatch(setFormResultAction(false)));
    };
}
