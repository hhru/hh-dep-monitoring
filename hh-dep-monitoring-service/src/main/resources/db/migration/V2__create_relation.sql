CREATE TYPE priority AS ENUM (
    'CRITICAL',
    'PARTIAL',
    'OPTIONAL'
    );

CREATE TABLE IF NOT EXISTS relation (
    relation_id SERIAL PRIMARY KEY,
    repository_from_id BIGINT REFERENCES repository NOT NULL,
    repository_to_id BIGINT REFERENCES repository NOT NULL,
    description TEXT,
    priority priority NOT NULL
);
