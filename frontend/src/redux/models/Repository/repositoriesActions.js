import axios from 'axios';
import { addMessage } from 'redux/models/Notification/notificationsActions';
import { DEFAULT_PER_PAGE, REPOSITORY_URL } from 'Utils/constants';

export const FETCH_REPOSITORIES = 'FETCH_REPOSITORIES ';
export const FETCH_REPOSITORY = 'FETCH_REPOSITORY ';

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


export function fetchRepositories() {
    return (dispatch) => {
        axios.get(`${REPOSITORY_URL}/page?perPage=${DEFAULT_PER_PAGE}`)
            .then((result) => {
                dispatch(fetchRepositoriesAction(result.data));
            })
            .catch(() => {
                dispatch(addMessage('Error in fetching repositories', 'error'));
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
                dispatch(addMessage('Error in fetching repository data', 'error'));
            });
    };
}
