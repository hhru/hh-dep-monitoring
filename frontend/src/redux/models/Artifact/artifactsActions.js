import { ARTIFACT_TREE_URL } from 'Utils/constants';
import axios from 'axios';
import { addMessageAction } from 'redux/models/Notification/notificationsActions';

export const FETCH_ARTIFACTS_TREE = 'FETCH_ARTIFACTS_TREE';
export const FETCH_ARTIFACTS = 'FETCH_ARTIFACTS';

export const fetchArtifactsTreeAction = artifactsTree => ({
    type: FETCH_ARTIFACTS_TREE,
    payload: artifactsTree,
});

export const fetchArtifactsAction = artifactNames => ({
    type: FETCH_ARTIFACTS,
    payload: artifactNames,
});

export function fetchArtifactsTree() {
    return (dispatch) => {
        axios.get(`${ARTIFACT_TREE_URL}full-tree`)
            .then((response) => {
                dispatch(fetchArtifactsTreeAction(response.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Can\'t get artifact tree data', 'error'));
            });
    };
}

export function fetchArtifacts() {
    return (dispatch) => {
        axios.get(`${ARTIFACT_TREE_URL}names`)
            .then((response) => {
                dispatch(fetchArtifactsAction(response.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Can\'t get artifact data', 'error'));
            });
    };
}
