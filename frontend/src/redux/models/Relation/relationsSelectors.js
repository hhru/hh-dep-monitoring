export function getRelations(store, repositoryId) {
    const relationsByPriority = store.relations.relationsByRepoId[repositoryId];
    let relations = [];
    relationsByPriority && Object.keys(relationsByPriority).forEach((priority) => {
        relations = relations.concat(relationsByPriority[priority]);
    });
    return relations;
}

export function getRelation(store, repositoryId, relationId) {
    return getRelations(store, repositoryId).find(item => item.relationId === relationId);
}
