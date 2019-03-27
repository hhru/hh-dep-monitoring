export const RELATIONS_BY_REPO_ID_DATA = 'RELATIONS_BY_REPO_ID_DATA';

export const addRelations = (repositoryId, relations) => ({
    type: RELATIONS_BY_REPO_ID_DATA,
    payload: {
        repositoryId,
        relations,
    },
});

const relationsData = {
    items: [
        {
            relationId: 1,
            repositoryFromId: 1231,
            repositoryToId: 543634,
            repositoryToName: 'money-transfer',
            priority: 'CRITICAL',
            description: 'Тут какое-то описание связи, например, что без этого ничего не будет запускаться',
            hasRelations: true,
        },
        {
            relationId: 2,
            repositoryFromId: 1231,
            repositoryToId: 54675,
            repositoryToName: 'git-tasks',
            priority: 'OPTIONAL',
            description: 'Тут тоже описание',
            hasRelations: false,
        },
        {
            relationId: 3,
            repositoryFromId: 1231,
            repositoryToId: 3456,
            repositoryToName: 'JS_Task1',
            priority: 'PARTIAL',
            description: '',
            hasRelations: true,
        },
        {
            relationId: 4,
            repositoryFromId: 1231,
            repositoryToId: 2562,
            repositoryToName: 'jersey-hometask',
            priority: 'CRITICAL',
            description: 'Описание связи',
            hasRelations: true,
        },
    ],
};

export function fetchRelationsByRepoId(repositoryId) {
    return (dispatch) => {
        dispatch(addRelations(repositoryId, relationsData));
    };
}
