export const getReposForSearch = (store, repositoryFor) => store.repositories.list.items
    .filter(item => Number(repositoryFor) !== item.repositoryId)
    .map(item => ({
        value: item.repositoryId,
        label: item.name,
    }));

export const getRepositories = store => store.repositories.list;
