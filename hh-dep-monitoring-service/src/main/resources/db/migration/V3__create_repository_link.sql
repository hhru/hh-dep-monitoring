CREATE TABLE IF NOT EXISTS repository_link
(
  repository_link_id SERIAL PRIMARY KEY,
  repository_id      BIGINT REFERENCES repository NOT NULL,
  link_type          VARCHAR(255)                 NOT NULL,
  link_url           VARCHAR(255)                 NOT NULL
);
