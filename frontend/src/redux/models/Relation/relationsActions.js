import axios from 'axios';
import { RELATION_URL } from 'Utils/constants';
import { addMessageAction } from 'redux/models/Notification/notificationsActions';

export const FETCH_PRIORITY_TYPES = 'FETCH_PRIORITY_TYPES ';
export const FETCH_RELATIONS = 'FETCH_RELATIONS ';
export const ADD_RELATION = 'ADD_RELATION ';
export const EDIT_RELATION = 'EDIT_RELATION ';
export const DELETE_RELATION = 'DELETE_RELATION';
export const SET_FORM_RESULT = 'SET_FORM_RESULT';
export const RESET_FORM_RESULT = 'RESET_FORM_RESULT';

export const fetchPriorityTypesAction = priorityList => ({
    type: FETCH_PRIORITY_TYPES,
    payload: {
        priorityList,
    },
});

export const fetchRelationsAction = (repositoryId, relations) => ({
    type: FETCH_RELATIONS,
    payload: {
        repositoryId,
        relations,
    },
});

export const addRelationAction = (repositoryId, newRelation) => ({
    type: ADD_RELATION,
    payload: {
        repositoryId,
        newRelation,
    },
});

export const editRelationAction = (repositoryId, relation) => ({
    type: EDIT_RELATION,
    payload: {
        repositoryId,
        relation,
    },
});

export const deleteRelationAction = (repositoryId, relationId) => ({
    type: DELETE_RELATION,
    payload: {
        repositoryId,
        relationId,
    },
});

export const setFormResultAction = (result, action) => ({
    type: SET_FORM_RESULT,
    payload: {
        saveResult: result,
        actionType: action,
    },
});

export const resetFormResultAction = () => ({
    type: RESET_FORM_RESULT,
});

export function fetchPriorityTypes() {
    return (dispatch) => {
        axios.get(`${RELATION_URL}types`)
            .then((response) => {
                dispatch(fetchPriorityTypesAction(response.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Can\'t get priority types for relations', 'error'));
            });
    };
}

export function fetchRelations(repositoryId) {
    return (dispatch) => {
        axios.get(`${RELATION_URL}depend-on/${repositoryId}`)
            .then((response) => {
                dispatch(fetchRelationsAction(repositoryId, response.data));
            })
            .catch(() => {
                dispatch(addMessageAction('Error in fetching relations', 'error'));
            });
    };
}

export function addRelation(repositoryFromId, repositoryToId, priority, description) {
    return (dispatch) => {
        const newRelation = {
            repositoryFromId: Number(repositoryFromId),
            repositoryToId: Number(repositoryToId),
            priority,
            description,
        };

        axios.post(RELATION_URL, newRelation)
            .then((response) => {
                dispatch(addRelationAction(repositoryFromId, response.data));
                dispatch(setFormResultAction(true, 'added'));
            })
            .catch(() => dispatch(setFormResultAction(false)));
    };
}

export function editRelation(relationId, repositoryFromId, repositoryToId, priority, description) {
    return (dispatch) => {
        const editedRelation = {
            repositoryFromId: Number(repositoryFromId),
            repositoryToId: Number(repositoryToId),
            priority,
            description,
        };
        axios.put(RELATION_URL + relationId, editedRelation)
            .then((response) => {
                dispatch(editRelationAction(repositoryFromId, response.data));
                dispatch(setFormResultAction(true, 'updated'));
            })
            .catch(() => dispatch(setFormResultAction(false)));
    };
}

export function deleteRelation(relationId, repositoryId) {
    return (dispatch) => {
        axios.delete(RELATION_URL + relationId)
            .then(() => {
                dispatch(deleteRelationAction(repositoryId, relationId));
                dispatch(setFormResultAction(true, 'deleted'));
            })
            .catch(() => dispatch(setFormResultAction(false)));
    };
}

export function resetFormResult() {
    return (dispatch) => {
        dispatch(resetFormResultAction());
    };
}
