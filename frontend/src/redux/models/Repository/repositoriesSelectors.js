export const getReposForSearch = (store, repositoryFor) => store.repositories.list.items
    .filter(item => Number(repositoryFor) !== item.repositoryId)
    .map(item => ({
        value: item.repositoryId,
        label: item.name,
    }));

export const getRepositories = store => store.repositories.list;

export const getRepositoryLinks = (store, repositoryId) => {
    const repository = store.repositories.repositoryById[repositoryId]
        || store.repositories.list.items.find(item => Number(repositoryId) === item.repositoryId);
    const mainLink = {
        linkType: 'GITHUB',
        linkUrl: repository.htmlUrl,
        repositoryId,
        repositoryLinkId: 0,
    };
    return [mainLink, ...repository.linkUrls];
};
