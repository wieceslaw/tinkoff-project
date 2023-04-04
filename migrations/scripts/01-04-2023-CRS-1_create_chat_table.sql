--liquibase formatted sql

--changeset wieceslaw:CRS-1_create_chat_table
CREATE TABLE chat
(
    id BIGINT PRIMARY KEY
);
