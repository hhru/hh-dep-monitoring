import { RELATIONS_BY_REPO_ID_DATA } from './relationsActions';
import createReducer from '../../createReducer';

export const initialState = {
    relationsByRepoId: {},
};

export const relationsReducer = createReducer(initialState, {
    [RELATIONS_BY_REPO_ID_DATA]: (state, action) => {
        const { repositoryId, relations } = action.payload;
        return {
            ...state,
            relationsByRepoId: {
                ...state.relationsByRepoId,
                [repositoryId]: relations,
            },
        };
    },
});
