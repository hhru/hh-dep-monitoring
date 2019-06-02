import axios from 'axios';
import { EVENT_URL, DEFAULT_EVENTS_COUNT } from 'Utils/constants';
import { addMessageAction } from 'redux/models/Notification/notificationsActions';

export const FETCH_EVENTS = 'FETCH_EVENTS ';

export const fetchEventsAction = (repositoryId, events) => ({
    type: FETCH_EVENTS,
    payload: {
        repositoryId,
        events,
    },
});

export function fetchEvents(repositoryId) {
    return (dispatch) => {
        axios.get(`${EVENT_URL}for-repository/${repositoryId}/last/${DEFAULT_EVENTS_COUNT}`)
            .then((response) => {
                dispatch(fetchEventsAction(repositoryId, { items: response.data }));
            })
            .catch(() => {
                dispatch(addMessageAction('Error in fetching events', 'error'));
            });
    };
}
