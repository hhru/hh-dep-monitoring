export const getReposForSearch = (store, repositoryFor) => store.repositories.list.items
    && store.repositories.list.items.filter(item => Number(repositoryFor) !== item.repositoryId)
        .map(item => ({
            value: item.repositoryId,
            label: item.name,
        }));

export const getRepositories = store => store.repositories.list;

export const getRepositoriesPages = store => store.repositories.pages;

export const getRepositoryLinks = (store, repositoryId) => {
    let repository = store.repositories.repositoryById[repositoryId];
    if (repository === undefined) {
        const { pages } = store.repositories;
        Object.keys(pages).some((page) => {
            repository = pages[page].items.find(item => item.repositoryId === repositoryId);
            return !!repository;
        });
    }
    const mainLink = {
        linkType: 'GITHUB',
        linkUrl: repository.htmlUrl,
        repositoryId,
        repositoryLinkId: 0,
    };
    return [mainLink, ...repository.linkUrls];
};
