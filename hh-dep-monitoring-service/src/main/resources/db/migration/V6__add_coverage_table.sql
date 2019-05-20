CREATE TABLE IF NOT EXISTS coverage
(
  coverage_id SERIAL PRIMARY KEY,
  repository_id BIGINT REFERENCES repository,
  analize_time TIMESTAMPTZ NOT NULL,
  coverage REAL NOT NULL,
  source_type VARCHAR(32) NOT NULL
)
