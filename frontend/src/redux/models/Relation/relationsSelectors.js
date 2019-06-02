export function getRelations(store, repositoryId) {
    const priorityOrder = ['CRITICAL', 'PARTIAL', 'OPTIONAL'];
    const relationsByPriority = store.relations.relationsByRepoId[repositoryId];
    let relations = [];
    relationsByPriority && priorityOrder.forEach((priority) => {
        relationsByPriority[priority] && (relations = relations.concat(relationsByPriority[priority]));
    });
    return relations;
}

export function getRelation(store, repositoryId, relationId) {
    return getRelations(store, repositoryId).find(item => item.relationId === relationId);
}
