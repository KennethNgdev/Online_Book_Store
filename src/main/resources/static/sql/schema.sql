drop table if exists roles cascade;
drop table if exists book cascade;
drop table if exists comment cascade;
drop table if exists users cascade;
create table BOOK
(
    AVAILABILITY BOOLEAN not null,
    PRICE        REAL    not null,
    BOOK_ID      BIGINT auto_increment
        primary key,
    AUTHOR       CHARACTER VARYING(255),
    BOOK_NAME    CHARACTER VARYING(255),
    DESCRIPTION  CHARACTER VARYING(255),
    COVER_PHOTO  BINARY LARGE OBJECT
);

create table USERS
(
    USER_ID          BIGINT auto_increment
        primary key,
    DELIVERY_ADDRESS CHARACTER VARYING(255),
    EMAIL            CHARACTER VARYING(255),
    FIRST_NAME       CHARACTER VARYING(255),
    LAST_NAME        CHARACTER VARYING(255),
    PASSWORD         CHARACTER VARYING(255),
    USERNAME         CHARACTER VARYING(255)
);

create table COMMENT
(
    COMMENT_ID BIGINT auto_increment
        primary key,
    TICKET_ID  BIGINT,
    USER_ID    BIGINT,
    CONTEXT    CHARACTER VARYING(255),
    constraint FKH57Y35T42NI0UUM64H4RNWAU8
        foreign key (TICKET_ID) references BOOK,
    constraint FKQM52P1V3O13HY268HE0WCNGR5
        foreign key (USER_ID) references USERS
);

create table ORDERS
(
    ORDER_ID         BIGINT auto_increment
        primary key,
    DATE             TIMESTAMP,
    DELIVERY_ADDRESS CHARACTER VARYING(255),
    TOTAL_PRICE      BIGINT,
    TOTAL_QUANTITY   BIGINT,
    USER_ID          BIGINT,
    constraint FK32QL8UBNTJ5UH44PH9659TIIH
        foreign key (USER_ID) references USERS
);

create table ORDER_ITEM
(
    ORDER_ITEM_ID BIGINT auto_increment
        primary key,
    BOOK_ID       BIGINT,
    ORDER_ID      BIGINT,
    QUANTITY      INTEGER not null,
    constraint FKB033AN1F8QMPBNFL0A6JB5NJS
        foreign key (BOOK_ID) references BOOK,
    constraint FKT4DC2R9NBVBUJRLJV3E23IIBT
        foreign key (ORDER_ID) references ORDERS
);

create table ROLES
(
    ADMIN_ID BIGINT auto_increment
        primary key,
    USER_ID  BIGINT,
    ROLE     CHARACTER VARYING(255),
    constraint FKGC8DTQL9MKQ268DETXIOX7FPM
        foreign key (USER_ID) references USERS
);

