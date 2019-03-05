CREATE TYPE priority AS ENUM (
    'critical',
    'partial',
    'optional'
);

CREATE TABLE IF NOT EXISTS relation (
    relation_id SERIAL PRIMARY KEY,
    repository_from_id INT REFERENCES repository NOT NULL,
    repository_to_id INT REFERENCES repository NOT NULL,
    description TEXT,
    priority priority NOT NULL
);
