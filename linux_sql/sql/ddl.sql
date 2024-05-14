--ddl.sql pseudocode/steps
--you can assume database is already created mannually
--1. switch to `host_agent` Shen: I think this is not needed? since its specified in -d
--2. create `host_info` table if not exist
--3. create `host_usage` table if not exist

--Execute ddl.sql script on the host_agent database againse the psql instance
--psql -h localhost -U postgres -d host_agent -f sql/ddl.sql# ddl.sql pseudocode/steps

CREATE TABLE IF NOT EXISTS PUBLIC.host_info
(
    id               SERIAL NOT NULL,
    hostname         VARCHAR NOT NULL,
    cpu_number       INT2 NOT NULL,
    cpu_architecture VARCHAR NOT NULL,
    cpu_model        VARCHAR NOT NULL,
    cpu_mhz          FLOAT8 NOT NULL,
    l2_cache         INT4 NOT NULL,
    "timestamp"      TIMESTAMP NULL,
    total_mem        INT4 NULL,
    CONSTRAINT host_info_pk PRIMARY KEY (id),
    CONSTRAINT host_info_un UNIQUE (hostname)
);

-- Create `host_usage` table if not exists
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage
(
    "timestamp"    TIMESTAMP NOT NULL,
    host_id        SERIAL NOT NULL,
    memory_free    INT4 NOT NULL,
    cpu_idle       INT2 NOT NULL,
    cpu_kernel     INT2 NOT NULL,
    disk_io        INT4 NOT NULL,
    disk_available INT4 NOT NULL,
    CONSTRAINT host_usage_host_info_fk FOREIGN KEY (host_id) REFERENCES host_info(id)
);
-- Indicate script has run successfully
DO $$
BEGIN
    RAISE NOTICE 'Script executed successfully.';
END
$$;