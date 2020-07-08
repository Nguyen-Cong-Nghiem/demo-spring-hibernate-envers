-- liquibase formatted sql
-- changeset nghiemnc:1.1

drop table if exists "sp_enver_author";
drop table if exists "sp_enver_book";

CREATE TABLE "sp_enver_author"
(
    "id"         serial primary key,
    "name"       varchar(255),
    "type"       varchar(255),
    "created_at" timestamp,
    "updated_at" timestamp,
    "created_by" int8,
    "updated_by" int8
);

CREATE TABLE "sp_enver_book"
(
    "id"         serial primary key,
    "name"       varchar(255),
    "type"       varchar(255),
    "author_id"  bigint,
    "created_at" timestamp,
    "updated_at" timestamp,
    "created_by" int8,
    "updated_by" int8
);

-- create table "revision_info"
-- (
--     "revision_id"   serial primary key,
--     "rev_timestamp" bigint,
--     "username"      varchar(255)
-- );
-- create table "sp_enver_author_aud"
-- (
--     "id"            bigint,
--     "revision_id"   int4,
--     "revision_type" int2,
--     "name"          varchar(255),
--     "type"          varchar(255),
--     "created_at"    timestamp,
--     "updated_at"    timestamp,
--     "created_by"    int8,
--     "updated_by"    int8,
--     PRIMARY KEY (id, revision_id),
--     constraint sp_enver_author_aud_revinfo foreign key (revision_id) references revision_info (revision_id) match simple on update no action on delete no action
-- );
-- CREATE TABLE "sp_enver_book_aud"
-- (
--     "id"            bigint,
--     "revision_id"   int4,
--     "revision_type" int2,
--     "author_id"     bigint,
--     "name"          varchar(255),
--     "type"          varchar(255),
--     "created_at"    timestamp,
--     "updated_at"    timestamp,
--     "created_by"    int8,
--     "updated_by"    int8,
--     PRIMARY KEY (id, revision_id),
--     constraint sp_enver_book_aud_revinfo foreign key (revision_id) references revision_info (revision_id) match simple on update no action on delete no action
-- );
