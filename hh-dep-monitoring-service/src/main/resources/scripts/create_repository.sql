CREATE TABLE IF NOT EXISTS repository (
    repository_id SERIAL PRIMARY KEY,
    name VARCHAR(110) NOT NULL,
    html_url VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    is_archived BOOL NOT NULL,
    is_active BOOL NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ
);
