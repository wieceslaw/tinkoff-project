--liquibase formatted sql

--changeset wieceslaw:CRS-2_create_link_sequence
CREATE SEQUENCE link_id_seq;


--changeset wieceslaw:CRS-2_create_link_table
CREATE TABLE IF NOT EXISTS link
(
    id                BIGINT PRIMARY KEY                DEFAULT nextval('link_id_seq'),
    url               TEXT UNIQUE              NOT NULL,
    last_check_time   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    last_update_time TIMESTAMP WITH TIME ZONE
);
