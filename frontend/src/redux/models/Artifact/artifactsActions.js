export const FETCH_ARTIFACTS = 'FETCH_ARTIFACTS';

export const fetchArtifactTreeAction = artifacts => ({
    type: FETCH_ARTIFACTS,
    payload: artifacts,
});

export function fetchArtifactTree() {
    const payload = [
        {
            type: 'Repository',
            name: 'gg-dep-monitoring',
            description: 'description description description description',
            repositoryId: 774400,
            dependencies: [
                {
                    type: 'Dependency',
                    dependencyId: 1,
                    parentDependencyId: null,
                    artifactVersion: {
                        type: 'ArtifactVersion',
                        artifactVersionId: 123,
                        version: '1.2.654',
                        artifact:
                            {
                                type: 'Artifact',
                                artifactId: 789,
                                repositoryId: 177441,
                                artifactName: 'artifactName_1',
                                groupName: 'groupName_1',
                            },
                    },
                    children:
                        [
                            {
                                type: 'Dependency',
                                dependencyId: 2,
                                parentDependencyId: null,
                                artifactVersion: {
                                    type: 'ArtifactVersion',
                                    artifactVersionId: 123,
                                    version: '1.2.654',
                                    artifact:
                                        {
                                            type: 'Artifact',
                                            artifactId: 789,
                                            repositoryId: 177441,
                                            artifactName: 'artifactName_1',
                                            groupName: 'groupName_1',
                                        },
                                },
                                children:
                                [],

                            },
                            {
                                type: 'Dependency',
                                dependencyId: 3,
                                parentDependencyId: null,
                                artifactVersion: {
                                    type: 'ArtifactVersion',
                                    artifactVersionId: 123,
                                    version: '1.2.654',
                                    artifact:
                                        {
                                            type: 'Artifact',
                                            artifactId: 789,
                                            repositoryId: 177441,
                                            artifactName: 'artifactName_1',
                                            groupName: 'groupName_1',
                                        },
                                },
                                children:
                                    [],
                            },
                        ],
                },
                {
                    type: 'Dependency',
                    dependencyId: 4,
                    parentDependencyId: null,
                    artifactVersion: {
                        type: 'ArtifactVersion',
                        artifactVersionId: 456,
                        version: '54.69.6',
                        artifact:
                            {
                                type: 'Artifact',
                                artifactId: 1,
                                repositoryId: 177441,
                                artifactName: 'artifactName_1',
                                groupName: 'groupName_1',
                            },
                    },
                    children:
                        [],
                },
            ],
        },
        {
            type: 'Repository',
            name: 'rr-dep-monitoring',
            description: 'description description description description',
            repositoryId: 825839,
            dependencies:
                [],
        },

    ];

    return (dispatch) => {
        dispatch(fetchArtifactTreeAction(payload));
    };
}
