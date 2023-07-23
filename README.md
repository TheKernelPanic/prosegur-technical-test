# Prueba técnica - Prosegur

El proyecto consta de dos configuraciones de persistencia; Una con MariaDB para entorno local y otra con H2 para el desarrollo de pruebas.

[Diagrama UML ilustrativo](https://www.plantuml.com/plantuml/png/bL7HIWCn47pFL-JHAla1aLBR2Wg2u7t1aTkU2xbisUm9-Nj56cboAuUFcTcPdUpialGiynICESn9Q0BkW3_HWoNdR8EzJEVmEtUWA4mX0DjRvxRjh56qzU0xd2cX46TZC0hmgVUWi0fPTlZGfwIcB4oRzK9qdXYEGfVdCuuVaAH1YzeOxTFtrhKdy_LRLzqro-m8SWtTTC8OHqkCJr5GykOFdok3MZb4t2pxN5DeWlMekhpqNMvKzMdz0jB4gu4rqNz36oNUjyKh9daerDQUC0wF8UppM-b4BqbnAkYFnpuV8zRxTn27UGfV)

## Inicialización del entorno local con MariaDB

Para ello crear el fichero _.env_ y ejecutar _docker-compose_:
```bash
cp ./docker/.env.dist ./docker/.env
```
```bash
docker-compose -p technical_test_prosegur -f ./docker/docker-compose.yaml --env-file ./docker/.env up -d
```

## Scripts de inicialización de la base de datos con H2

Script para la creación de la estructura de la base de datos:

[src/test/resources/sql/schema.sql](src/test/resources/sql/schema.sql)

Script para la inserción del conjunto de datos de prueba:

[src/test/resources/sql/data.sql](src/test/resources/sql/data.sql)
