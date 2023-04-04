--liquibase formatted sql


--changeset wieceslaw:CRS-3_subscription_table
CREATE TABLE subscription
(
    chat_id BIGINT REFERENCES chat ON DELETE CASCADE,
    link_id BIGINT REFERENCES link ON DELETE RESTRICT
);
