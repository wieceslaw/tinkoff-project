--liquibase formatted sql


--changeset wieceslaw:CRS-5_add_unique_constraint_to_link_url
ALTER  TABLE  link  ADD  UNIQUE (url);