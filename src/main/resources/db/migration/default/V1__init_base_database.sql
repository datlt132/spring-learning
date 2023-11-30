CREATE TABLE tenant
(
    tenant_id   character varying(255) COLLATE pg_catalog."default" NOT NULL,
    schema_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT tenant_pkey PRIMARY KEY (tenant_id)
);

INSERT INTO tenant (tenant_id, schema_name)
values ('tenant-id-1', 'schema_tenant_1');
INSERT INTO tenant (tenant_id, schema_name)
values ('tenant-id-2', 'schema_tenant_2');