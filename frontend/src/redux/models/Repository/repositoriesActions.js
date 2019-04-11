import axios from 'axios';
import { DEFAULT_PER_PAGE, REPOSITORY_URL } from 'Utils/Constants';

export const REPOSITORIES_DATA = 'REPOSITORIES_DATA';
export const REPOSITORY_DATA_BY_ID = 'REPOSITORY_DATA_BY_ID';

export const addRepositories = repositories => ({
    type: REPOSITORIES_DATA,
    payload: repositories,
});

export const addRepository = (repositoryId, repositoryData) => ({
    type: REPOSITORY_DATA_BY_ID,
    payload: {
        repositoryId,
        repositoryData,
    },
});


export function fetchRepositories() {
    return (dispatch) => {
        axios.get(`${REPOSITORY_URL}/page?perPage=${DEFAULT_PER_PAGE}`)
            .then((result) => {
                dispatch(addRepositories(result.data));
            })
            .catch((error) => { console.error(error); });
    };
}


export function fetchRepositoryById(id) {
    return (dispatch) => {
        axios.get(`${REPOSITORY_URL}/${id}`)
            .then((result) => {
                dispatch(addRepository(id, result.data));
            })
            .catch((error) => { console.error(error); });
    };
}
