CREATE TABLE users
(
    id           bigint not null,
    full_name    character varying(255) COLLATE pg_catalog."default",
    email        character varying(255) COLLATE pg_catalog."default",
    password     character varying(255) COLLATE pg_catalog."default",
    active       smallint,
    deleted      smallint,
    created_by   bigint,
    updated_by   bigint,
    created_time timestamp,
    updated_time timestamp,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);