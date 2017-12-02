# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  email                         varchar(255) not null,
  password                      varchar(255),
  name                          varchar(255),
  surname                       varchar(255),
  type                          integer,
  constraint ck_account_type check ( type in (0,1)),
  constraint pk_account primary key (email)
);

create table author (
  id                            integer auto_increment not null,
  name                          varchar(255),
  surname                       varchar(255),
  description                   varchar(255),
  constraint pk_author primary key (id)
);

create table book (
  id                            integer auto_increment not null,
  name                          varchar(255),
  image_url                     varchar(255),
  author_id                     integer,
  category_id                   integer,
  price                         double,
  page_count                    integer,
  description                   varchar(255),
  constraint pk_book primary key (id)
);

create table category (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint pk_category primary key (id)
);

create table order (
  id                            integer auto_increment not null,
  account_id                    varchar(255),
  date                          datetime(6),
  address                       varchar(255),
  phone                         varchar(255),
  zip                           integer,
  status                        integer,
  answer                        varchar(255),
  constraint ck_order_status check ( status in (0,1,2,3)),
  constraint pk_order primary key (id)
);


# --- !Downs

drop table if exists account;

drop table if exists author;

drop table if exists book;

drop table if exists category;

drop table if exists order;

