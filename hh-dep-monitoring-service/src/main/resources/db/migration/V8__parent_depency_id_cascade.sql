ALTER TABLE dependency
    DROP COLUMN IF EXISTS parent_dependency_id CASCADE,
    ADD COLUMN parent_dependency_id INTEGER REFERENCES dependency ON DELETE CASCADE;
