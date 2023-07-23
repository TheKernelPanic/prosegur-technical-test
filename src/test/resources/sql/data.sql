INSERT INTO _customer
(id, name, first_surname, second_surname, ori_entity, dni)
VALUES
    (1, 'Alfredo', 'Gomez', 'Lopez', 'Banco Santander', '11111111B'),
    (2, 'Maria', 'Gutierrez', 'Banco', 'Banco Santander', '22222222C'),
    (3, 'Leyre', 'Quesada', 'Julián', 'Ibercaja', '33333333D'),
    (4, 'Juana', 'Díez', 'Llamas', 'Ibercaja', '44444444E');

INSERT INTO _attribute
(id, description, weight)
VALUES
    (1, 'Situación Laboral', 30),
    (2, 'País Fiscal', 20),
    (3, 'Origen de los Fondos', 30),
    (4, 'Saldo Medio Semestral', 20);

INSERT INTO _value
(id, description, weight, attribute_id)
VALUES
    (1, 'Trabajador Cuenta Ajena', 10, (SELECT id FROM _attribute WHERE description LIKE 'Situación Laboral' LIMIT 1)),
    (2, 'Trabajador Cuenta Propia', 100, (SELECT id FROM _attribute WHERE description LIKE 'Situación Laboral' LIMIT 1)),
    (3, 'Desempleado', 50, (SELECT id FROM _attribute WHERE description LIKE 'Situación Laboral' LIMIT 1)),
    (4, 'España', 0, (SELECT id FROM _attribute WHERE description LIKE 'País Fiscal' LIMIT 1)),
    (5, 'Luxemburgo', 100, (SELECT id FROM _attribute WHERE description LIKE 'País Fiscal' LIMIT 1)),
    (6, 'Rendimientos del Trabajo', 10, (SELECT id FROM _attribute WHERE description LIKE 'Origen de los Fondos' LIMIT 1)),
    (7, 'Rentas', 50, (SELECT id FROM _attribute WHERE description LIKE 'Origen de los Fondos' LIMIT 1)),
    (8, 'Inversiones', 75, (SELECT id FROM _attribute WHERE description LIKE 'Origen de los Fondos' LIMIT 1)),
    (9, 'Menor de 5.000€', 0, (SELECT id FROM _attribute WHERE description LIKE 'Saldo Medio Semestral' LIMIT 1)),
    (10, 'Mayor de 5.000€ y Menor de 50.000€', 25, (SELECT id FROM _attribute WHERE description LIKE 'Saldo Medio Semestral' LIMIT 1)),
    (11, 'Mayor de 50.000€ y Menor de 100.000€', 50, (SELECT id FROM _attribute WHERE description LIKE 'Saldo Medio Semestral' LIMIT 1)),
    (12, 'Mayor de 100.000€', 100, (SELECT id FROM _attribute WHERE description LIKE 'Saldo Medio Semestral' LIMIT 1));

INSERT INTO _customer_value
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