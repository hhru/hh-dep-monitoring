import createReducer from 'redux/createReducer';
import { FETCH_PRIORITY_TYPES,
    FETCH_RELATIONS,
    ADD_RELATION,
    EDIT_RELATION,
    DELETE_RELATION } from './relationsActions';

export const initialState = {
    relationsByRepoId: {},
    priorityTypes: undefined,
};

const getRelation = (relations, relationId) => {
    let relation;
    relations && Object.keys(relations).some((priority) => {
        relation = relations[priority].find(item => item.relationId === relationId);
        return !!relation;
    });
    return relation || {};
};

export const relationsReducer = createReducer(initialState, {
    [FETCH_PRIORITY_TYPES]: (state, action) => {
        const { priorityList } = action.payload;
        return {
            ...state,
            priorityTypes: priorityList,
        };
    },
    [FETCH_RELATIONS]: (state, action) => {
        const { repositoryId, relations } = action.payload;
        return {
            ...state,
            relationsByRepoId: {
                ...state.relationsByRepoId,
                [repositoryId]: relations,
            },
        };
    },
    [ADD_RELATION]: (state, action) => {
        const { repositoryId, newRelation } = action.payload;
        const newState = Object.assign({}, state);
        !newState.relationsByRepoId[repositoryId] && (newState.relationsByRepoId[repositoryId] = {});
        newState.relationsByRepoId[repositoryId][newRelation.priority] = [].concat(
            newState.relationsByRepoId[repositoryId][newRelation.priority] || [], newRelation,
        );
        return newState;
    },
    [EDIT_RELATION]: (state, action) => {
        const { repositoryId, relation } = action.payload;
        const newState = Object.assign({}, state);
        const relations = newState.relationsByRepoId[repositoryId];
        const currentRelation = getRelation(relations, relation.relationId);

        if (relation.priority === currentRelation.priority) {
            relations[relation.priority] = relations[relation.priority]
                .map(item => (item.relationId === relation.relationId ? relation : item));
        } else {
            relations[currentRelation.priority] = relations[currentRelation.priority]
                .filter(item => item.relationId !== currentRelation.relationId);
            relations[relation.priority] = [].concat(relations[relation.priority] || [], relation);
        }
        return newState;
    },
    [DELETE_RELATION]: (state, action) => {
        const { repositoryId, relationId } = action.payload;
        const newState = Object.assign({}, state);
        const relations = newState.relationsByRepoId[repositoryId];
        const relation = getRelation(relations, relationId);

        relations[relation.priority] = relations[relation.priority]
            .filter(item => item.relationId !== relationId);
        return newState;
    },
});
