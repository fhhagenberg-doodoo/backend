--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- auto-generated definition
create table doodoo
(
    id          varchar(255) default uuid_generate_v4() not null
        constraint doodoo_pk
            primary key,
    name        varchar(255),
    description varchar(255),
    due_date    timestamp,
    done_since  timestamp,
    priority    integer      default 1                  not null
);

-- alter table doodoo
--     owner to postgres;

create unique index doodoo_id_uindex
    on doodoo (id);