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