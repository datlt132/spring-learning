CREATE TABLE users
(
    id           serial primary key,
    full_name    character varying(255) COLLATE pg_catalog."default",
    email        character varying(255) COLLATE pg_catalog."default",
    password     character varying(255) COLLATE pg_catalog."default",
    active       boolean NOT NULL DEFAULT true,
    deleted      boolean NOT NULL DEFAULT false,
    created_by   bigint,
    updated_by   bigint,
    created_time timestamp,
    updated_time timestamp
);