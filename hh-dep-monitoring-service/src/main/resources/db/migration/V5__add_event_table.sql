CREATE TABLE IF NOT EXISTS public.event
(
  event_id SERIAL PRIMARY KEY,
  created_at TIMESTAMPTZ NOT NULL,
  repository_id BIGINT REFERENCES repository,
  artifact_id INTEGER REFERENCES artifact,
  type VARCHAR(32) NOT NULL,
  description VARCHAR(500)
)
