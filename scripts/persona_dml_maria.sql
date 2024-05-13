INSERT INTO 
	`persona_db`.`persona`(`cc`,`nombre`,`apellido`,`genero`,`edad`) 
VALUES
	(123456789,'Pepe','Perez','M',30),
	(987654321,'Pepito','Perez','M',null),
	(321654987,'Pepa','Juarez','F',30),
	(147258369,'Pepita','Juarez','F',10),
	(963852741,'Fede','Perez','M',18);

-- Insertar en la tabla profesion
INSERT INTO profesion (id, nom, des) VALUES
                                         (1, 'Ingeniería', 'Ingeniería en sistemas'),
                                         (2, 'Medicina', 'Medicina general'),
                                         (3, 'Derecho', 'Estudios jurídicos'),
                                         (4, 'Arquitectura', 'Diseño y construcción de edificios'),
                                         (5, 'Biología', 'Estudio de organismos vivos');

-- Insertar en la tabla telefono
INSERT INTO telefono (num, oper, duenio) VALUES
                                             ('3001234567', 'Movistar', 123456789),
                                             ('3102345678', 'Claro', 987654321),
                                             ('3203456789', 'Tigo', 321654987),
                                             ('3304567890', 'Movistar', 147258369),
                                             ('3405678901', 'Claro', 963852741);

-- Insertar en la tabla estudios
INSERT INTO estudios (id_prof, cc_per, fecha, univer) VALUES
                                                          (1, 123456789, '2023-01-01', 'Universidad Nacional'),
                                                          (2, 987654321, '2022-05-01', 'Universidad de Los Andes'),
                                                          (3, 321654987, '2021-08-01', 'Universidad Javeriana'),
                                                          (4, 147258369, '2020-02-01', 'Universidad de Antioquia'),
                                                          (5, 963852741, '2019-09-01', 'Universidad del Valle');

select * from telefono;
select * from estudios;
show tables;