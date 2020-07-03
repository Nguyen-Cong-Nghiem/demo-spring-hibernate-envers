-- liquibase formatted sql
-- changeset nghiemnc:1.1

drop table if exists "sp_enver_author";
drop table if exists "sp_enver_book";
-- drop table if exists "sp_enver_book_aud";
-- drop table if exists "revinfo";
-- drop table if exists "sp_enver_author_aud";


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

-- create table "revinfo"
-- (
--     "id"       bigint,
--     "rev"      int4 not null,
--     "revtstmp" bigint,
--     "username" varchar(255),
--     constraint revinfo_pkey PRIMARY KEY (rev)
-- );
--
--
-- create table "sp_enver_author_aud"
-- (
--     "id"         bigint,
--     "rev"        int4,
--     "revtype"    int2,
--     "name"       varchar(255),
--     "type"       varchar(255),
--     "created_at" timestamp,
--     "updated_at" timestamp,
--     "created_by" int8,
--     "updated_by" int8,
--     PRIMARY KEY (id, rev),
--     constraint sp_enver_author_aud_revinfo foreign key (rev) references revinfo (rev) match simple on update no action on delete no action
-- );
--
-- CREATE TABLE "sp_enver_book_aud"
-- (
--     "id"         bigint,
--     "rev"        int4,
--     "revtype"    int2,
--     "author_id"  bigint,
--     "name"       varchar(255),
--     "type"       varchar(255),
--     "created_at" timestamp,
--     "updated_at" timestamp,
--     "created_by" int8,
--     "updated_by" int8,
--     PRIMARY KEY (id, rev),
--     constraint sp_enver_book_aud_revinfo foreign key (rev) references revinfo (rev) match simple on update no action on delete no action
-- );