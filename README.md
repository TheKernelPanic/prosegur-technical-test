# Prueba técnica - Prosegur

## Inicialización del entorno local

Configuración del servidor del bases de datos mediante el uso de docker, en este caso MariaDB.

Crear el fichero de variables de entorno usadas por la configuración de docker.


Ejecutar en el directorio root del proyecto:
```bash
cp ./docker/.env.dist ./docker/.env
```
```bash
docker-compose -p technical_test_prosegur -f ./docker/docker-compose.yaml --env-file ./docker/.env up -d
```

# Inicialización de la base de datos

Script de inicialización:
```sql
create table attribute
(
    weight int          not null,
    id     bigint auto_increment
        primary key,
    name   varchar(128) not null,
    constraint UKhpwum0iq12fs4ej5d0tgy6wsn
        unique (name)
);

create table customer
(
    id             bigint auto_increment
        primary key,
    dni            varchar(9)   not null,
    first_surname  varchar(50)  not null,
    name           varchar(50)  not null,
    second_surname varchar(50)  null,
    ori_entity     varchar(255) not null,
    constraint UK9jf7jfr0lltn86gmjn14l71d8
        unique (dni)
);

create table value
(
    attribute_id bigint       not null,
    id           bigint auto_increment
        primary key,
    value        varchar(255) not null,
    weight       varchar(255) not null,
    constraint FKkj1luvvc2k2de11cmmdt7taiu
        foreign key (attribute_id) references attribute (id)
);

create table customer_value
(
    customer_id bigint not null,
    value_id    bigint not null,
    primary key (customer_id, value_id),
    constraint FK8crovc3dol7fx2ckjupwep7u9
        foreign key (customer_id) references customer (id),
    constraint FKsvsjtsg3vk57gdyo8m9k12yvh
        foreign key (value_id) references value (id)
);
```