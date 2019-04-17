CREATE TABLE IF NOT EXISTS artifact (
    artifact_id SERIAL PRIMARY KEY,
    artifact_name VARCHAR(110) NOT NULL,
    group_name VARCHAR(255),
    repository_id BIGINT REFERENCES repository
);

CREATE TABLE IF NOT EXISTS artifact_version (
    artifact_version_id SERIAL PRIMARY KEY,
    artifact_id INTEGER REFERENCES artifact NOT NULL,
    version_major INTEGER,
    version_minor INTEGER,
    version_micro INTEGER,
    version VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS dependency (
    dependency_id SERIAL PRIMARY KEY,
    repository_id BIGINT REFERENCES repository NOT NULL,
    artifact_version_id INTEGER REFERENCES artifact_version NOT NULL
);
