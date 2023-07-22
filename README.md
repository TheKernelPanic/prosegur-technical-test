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
    name   varchar(128) not null
)
    collate = utf8mb4_general_ci;

create table clients
(
    id             bigint auto_increment
        primary key,
    dni            varchar(9)   not null,
    first_surname  varchar(50)  not null,
    name           varchar(50)  not null,
    second_surname varchar(50)  null,
    ori_entity     varchar(255) not null
)
    collate = utf8mb4_general_ci;

create table value
(
    attribute_id bigint       not null,
    id           bigint auto_increment
        primary key,
    value        varchar(255) not null,
    weight       varchar(255) not null,
    constraint FKkj1luvvc2k2de11cmmdt7taiu
        foreign key (attribute_id) references attribute (id)
)
    collate = utf8mb4_general_ci;

create table client_value
(
    client_id bigint not null,
    value_id  bigint not null,
    primary key (client_id, value_id),
    constraint FKcnbph9mfmsvf443cqqakc9dq5
        foreign key (value_id) references value (id),
    constraint FKegsqyl8kx0buq8chkrx840wir
        foreign key (client_id) references clients (id)
)
    collate = utf8mb4_general_ci;
```