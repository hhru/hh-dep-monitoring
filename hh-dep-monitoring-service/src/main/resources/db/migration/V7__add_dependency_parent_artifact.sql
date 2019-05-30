ALTER TABLE dependency ADD COLUMN parent_dependency_id INTEGER REFERENCES dependency;
