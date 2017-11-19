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


# --- !Downs

drop table if exists account;

