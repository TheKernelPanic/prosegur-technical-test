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
    weight       int not null,
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

## Script para la creación de los fixtures del documento de requerimientos

```sql 
INSERT INTO `customer`
(id, name, first_surname, second_surname, ori_entity, dni)
VALUES
    (1, "Alfredo", "Gomez", "Lopez", "Banco Santander", "11111111B"),
    (2, "Maria", "Gutierrez", "Banco", "Santander", "22222222C"),
    (3, "Leyre", "Quesada", "Julián", "Ibercaja", "33333333D"),
    (4, "Juana", "Díez", "Llamas", "Ibercaja", "44444444E");

INSERT INTO `attribute`
(id, name, weight)
VALUES
    (1, "Situación Laboral", 30),
    (2, "País Fiscal", 20),
    (3, "Origen de los Fondos", 30),
    (4, "Saldo Medio Semestral", 20);

INSERT INTO `value`
(id, value, weight, attribute_id)
VALUES
    (1, "Trabajador Cuenta Ajena", 10, (SELECT id FROM attribute WHERE name LIKE "Situación Laboral" LIMIT 1)),
    (2, "Trabajador Cuenta Propia", 100, (SELECT id FROM attribute WHERE name LIKE "Situación Laboral" LIMIT 1)),
    (3, "Desempleado", 50, (SELECT id FROM attribute WHERE name LIKE "Situación Laboral" LIMIT 1)),
    (4, "España", 0, (SELECT id FROM attribute WHERE name LIKE "País Fiscal" LIMIT 1)),
    (5, "Luxemburgo", 100, (SELECT id FROM attribute WHERE name LIKE "País Fiscal" LIMIT 1)),
    (6, "Rendimientos del Trabajo", 10, (SELECT id FROM attribute WHERE name LIKE "Origen de los Fondos" LIMIT 1)),
    (7, "Rentas", 50, (SELECT id FROM attribute WHERE name LIKE "Origen de los Fondos" LIMIT 1)),
    (8, "Inversiones", 75, (SELECT id FROM attribute WHERE name LIKE "Origen de los Fondos" LIMIT 1)),
    (9, "Menor de 5.000€", 0, (SELECT id FROM attribute WHERE name LIKE "Saldo Medio Semestral" LIMIT 1)),
    (10, "Mayor de 5.000€ y Menor de 50.000€", 25, (SELECT id FROM attribute WHERE name LIKE "Saldo Medio Semestral" LIMIT 1)),
    (11, "Mayor de 50.000€ y Menor de 100.000€", 50, (SELECT id FROM attribute WHERE name LIKE "Saldo Medio Semestral" LIMIT 1)),
    (12, "Mayor de 100.000€", 100, (SELECT id FROM attribute WHERE name LIKE "Saldo Medio Semestral" LIMIT 1));

INSERT INTO customer_value
(customer_id, value_id)
VALUES
    (1, 1),
    (1, 4),
    (1, 6),
    (1, 9),
    (2, 2),
    (2, 4),
    (2, 7),
    (2, 11),
    (3, 3),
    (3, 5),
    (3, 8),
    (3, 12),
    (4, 3),
    (4, 4),
    (4, 7),
    (4, 10);

```