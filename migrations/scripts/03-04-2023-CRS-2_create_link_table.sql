--liquibase formatted sql

--changeset wieceslaw:CRS-2_create_link_sequence
CREATE SEQUENCE link_id_seq;


--changeset wieceslaw:CRS-2_create_link_table
CREATE TABLE link(
    id BIGINT PRIMARY KEY DEFAULT nextval('link_id_seq'),
    url TEXT NOT NULL,
    chat_id BIGINT REFERENCES chat ON DELETE CASCADE
);
