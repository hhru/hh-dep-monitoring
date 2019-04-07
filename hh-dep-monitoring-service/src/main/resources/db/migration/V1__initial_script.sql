CREATE TABLE IF NOT EXISTS repository (
    repository_id BIGINT PRIMARY KEY,
    name VARCHAR(110) NOT NULL,
    html_url VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    archived BOOL NOT NULL,
    active BOOL NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ
);

CREATE TABLE IF NOT EXISTS relation (
    relation_id SERIAL PRIMARY KEY,
    repository_from_id BIGINT REFERENCES repository NOT NULL,
    repository_to_id BIGINT REFERENCES repository NOT NULL,
    description TEXT,
    priority VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS repository_link (
    repository_link_id SERIAL PRIMARY KEY,
    repository_id      BIGINT REFERENCES repository NOT NULL,
    link_type          VARCHAR(255)                 NOT NULL,
    link_url           VARCHAR(255)                 NOT NULL
);


