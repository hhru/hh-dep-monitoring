import axios from 'axios';
import { addMessageAction } from 'redux/models/Notification/notificationsActions';
import { DEFAULT_PER_PAGE, REPOSITORY_URL, REPOSITORY_LINK_URL } from 'Utils/constants';

export const FETCH_REPOSITORIES = 'FETCH_REPOSITORIES ';
export const FETCH_REPOSITORY = 'FETCH_REPOSITORY ';
export const FETCH_LINK_TYPES = 'FETCH_LINK_TYPES';
export const FETCH_REPOSITORY_LINKS = 'FETCH_REPOSITORY_LINKS';

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
    payload: linkTypes,
});

export const fetchRepositoryLinksAction = (repositoryId, links) => ({
    type: FETCH_REPOSITORY_LINKS,
    payload: {
        repositoryId,
        links,
    },
});

export function fetchRepositories() {
    return (dispatch) => {
        axios.get(`${REPOSITORY_URL}/page?perPage=${DEFAULT_PER_PAGE}`)
            .then((result) => {
                dispatch(fetchRepositoriesAction(result.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Error in fetching repositories', 'warning'));
            });
    };
}

export function fetchRepository(id) {
    return (dispatch) => {
        axios.get(`${REPOSITORY_URL}/${id}`)
            .then((result) => {
                dispatch(fetchRepositoryAction(id, result.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Error in fetching repository data', 'warning'));
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
                dispatch(addMessageAction('Can\'t get link types for relations', 'warning'));
            });
    };
}

export function fetchRepositoryLinks(repositoryId) {
    return (dispatch) => {
        axios.get(`${REPOSITORY_LINK_URL}for-repository/${repositoryId}`)
            .then((response) => {
                dispatch(fetchRepositoryLinksAction(repositoryId, response.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Can\'t get links for repository', 'warning'));
            });
    };
}
