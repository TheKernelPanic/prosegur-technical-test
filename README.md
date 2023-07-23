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

Script para la creación de la estructura de la base de datos:

[src/test/resources/sql/schema.sql](src/test/resources/sql/schema.sql)

Script para la inserción del conjunto de datos de prueba:

[src/test/resources/sql/data.sql](src/test/resources/sql/data.sql)
