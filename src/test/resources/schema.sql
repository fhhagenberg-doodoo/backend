CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- auto-generated definition
DROP TABLE IF EXISTS doodoo;

CREATE TABLE IF NOT EXISTS doodoo
(
    id          VARCHAR(255) DEFAULT uuid_generate_v4() NOT NULL
                CONSTRAINT doodoo_pk
                PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255),
    due_date    TIMESTAMP    WITH TIME ZONE,
    done_since  TIMESTAMP    WITH TIME ZONE,
    priority    INTEGER      DEFAULT 1                  NOT NULL
);

CREATE UNIQUE index doodoo_id_uindex
    ON doodoo (id);