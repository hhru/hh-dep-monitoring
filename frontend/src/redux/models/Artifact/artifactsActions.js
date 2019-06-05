import { ARTEFACT_TREE_URL } from 'Utils/constants';
import axios from 'axios';
import { addMessageAction } from 'redux/models/Notification/notificationsActions';

export const FETCH_ARTIFACTS = 'FETCH_ARTIFACTS';

export const fetchArtifactTreeAction = artifacts => ({
    type: FETCH_ARTIFACTS,
    payload: artifacts,
});

export function fetchArtifactTree() {
    return (dispatch) => {
        axios.get(`${ARTEFACT_TREE_URL}`)
            .then((response) => {
                dispatch(fetchArtifactTreeAction(response.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Can\'t get artefact tree data', 'error'));
            });
    };
}
