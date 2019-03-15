export const REPOSITORIES_DATA = 'REPOSITORIES_DATA';

export const addRepositories = repositories => ({
    type: REPOSITORIES_DATA,
    payload: repositories,
});

const repositoriesData = {
    items: [
        {
            repositoryId: 1231,
            name: 'HH_HTML',
            htmlUrl: 'https://github.com/SuhoyVasya/HH_HTML',
            description: '',
            isArchived: false,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2019-02-23T18:25:43',
        },
        {
            repositoryId: 543634,
            name: 'money-transfer',
            htmlUrl: 'https://github.com/SuhoyVasya/money-transfer',
            description: '',
            isArchived: false,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2019-03-17T12:25:43',
        },
        {
            repositoryId: 54675,
            name: 'git-tasks',
            htmlUrl: 'https://github.com/K-ksenia/git-tasks',
            description: 'Задания для школы программистов',
            isArchived: false,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2018-11-03T18:25:43',
        },
        {
            repositoryId: 2345234,
            name: 'maven-practice',
            htmlUrl: 'https://github.com/anickishin/maven-practice',
            description: 'Задания для школы программистов',
            isArchived: true,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2019-01-23T18:25:43',
        },
        {
            repositoryId: 3456,
            name: 'JS_Task1',
            htmlUrl: 'https://github.com/SuhoyVasya/JS_Task1',
            description: 'Здесь какое-нибудь длинное описание, например, чтоб в две строчки аж',
            isArchived: true,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2019-02-23T18:25:43',
        },
        {
            repositoryId: 23457,
            name: 'xml-xsl-xpath',
            htmlUrl: 'https://github.com/Afftobus/xml-xsl-xpath',
            description: 'aaabbbccc',
            isArchived: false,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2019-02-23T18:25:43',
        },
        {
            repositoryId: 2345,
            name: 'hibernate-hw',
            htmlUrl: 'https://github.com/Afftobus/hibernate-hw',
            description: '',
            isArchived: false,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2019-02-23T18:25:43',
        },
        {
            repositoryId: 2562,
            name: 'jersey-hometask',
            htmlUrl: 'https://github.com/SuhoyVasya/jersey-hometask',
            description: 'Сулим А.С.',
            isArchived: false,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2019-02-23T18:25:43',
        },
        {
            repositoryId: 2624,
            name: 'JS_Task2',
            htmlUrl: 'https://github.com/SuhoyVasya/JS_Task2',
            description: 'JS Task2',
            isArchived: true,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2019-03-13T18:25:43',
        },
        {
            repositoryId: 23465,
            name: 'hh-school',
            htmlUrl: 'https://github.com/K-ksenia/hh-school',
            description: 'homework',
            isArchived: false,
            createdAt: '2019-01-20T18:25:55',
            updatedAt: '2019-03-15T18:25:43',
        },
    ],
};

export function fetchRepositories() {
    return (dispatch) => {
        dispatch(addRepositories(repositoriesData));
    };
}
