drop table if exists _customer_value;
drop table if exists _value;
drop table if exists _attribute;
drop table if exists _customer;

create table _attribute
(
    weight      int          not null,
    id          bigint auto_increment
        primary key,
    description varchar(128) not null,
    constraint UKbcu84ar44jqjtifou7ot8i0ci
        unique (description)
);
create table _customer
(
    id             bigint auto_increment
        primary key,
    dni            varchar(9)   not null,
    first_surname  varchar(50)  not null,
    name           varchar(50)  not null,
    second_surname varchar(50)  null,
    ori_entity     varchar(255) not null,
    constraint UKa4aei4u3xq66egmvlhdni1rco
        unique (dni)
);

create table _value
(
    weight       int          not null,
    attribute_id bigint       not null,
    id           bigint auto_increment
        primary key,
    description  varchar(255) not null,
    constraint FKrfjunaq37wm97y0u1kkf2dufs
        foreign key (attribute_id) references _attribute (id)
);

create table _customer_value
(
    customer_id bigint not null,
    value_id    bigint not null,
    primary key (customer_id, value_id),
    constraint FK85tud76nlsgws6i7ybj0iecid
        foreign key (value_id) references _value (id),
    constraint FKrgpl3s77mkkdkbea2v5ei7h0t
        foreign key (customer_id) references _customer (id)
);