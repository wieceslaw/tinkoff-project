--liquibase formatted sql

--changeset wieceslaw:CRS-8_alter_link_table
ALTER TABLE link
    ALTER COLUMN last_update_time SET NOT NULL;