import createReducer from 'redux/createReducer';
import { FETCH_EVENTS } from './eventsActions';

export const initialState = {
    eventsByRepoId: {},
};

export const eventsReducer = createReducer(initialState, {
    [FETCH_EVENTS]: (state, action) => {
        const { repositoryId, events } = action.payload;
        return {
            ...state,
            eventsByRepoId: {
                ...state.eventsByRepoId,
                [repositoryId]: events,
            },
        };
    },
});
