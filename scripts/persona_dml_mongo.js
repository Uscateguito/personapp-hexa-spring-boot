use persona_db

// Create persona
db.persona.insertMany([
	{
		"_id": 123456789,
		"nombre": "Pepe",
		"apellido": "Perez",
		"genero": "M",
		"edad": 30,
		"telefonos": ["1"],
		"estudios": ["1"]
	},
	{
		"_id": 987654321,
		"nombre": "Pepito",
		"apellido": "Perez",
		"genero": "M",
		"edad": null,
		"telefonos": ["2"],
		"estudios": ["2"]
	},
	{
		"_id": 321654987,
		"nombre": "Pepa",
		"apellido": "Juarez",
		"genero": "F",
		"edad": 30,
		"telefonos": ["3"],
		"estudios": ["3"]
	},
	{
		"_id": 147258369,
		"nombre": "Pepita",
		"apellido": "Juarez",
		"genero": "F",
		"edad": 10,
		"telefonos": ["4"],
		"estudios": ["4"]
	},
	{
		"_id": 963852741,
		"nombre": "Fede",
		"apellido": "Perez",
		"genero": "M",
		"edad": 18,
		"telefonos": ["5"],
		"estudios": ["5"]
	}
])

// Create profesion

db.profesion.insertMany([
	{
		"_id": 1,
		"nom": "Ingeniería",
		"des": "Ingeniería en sistemas",
		"estudios": ["1"]
	},
	{
		"_id": 2,
		"nom": "Medicina",
		"des": "Medicina general",
		"estudios": ["2"]
	},
	{
		"_id": 3,
		"nom": "Derecho",
		"des": "Estudios jurídicos",
		"estudios": ["3"]
	},
	{
		"_id": 4,
		"nom": "Arquitectura",
		"des": "Diseño y construcción de edificios",
		"estudios": ["4"]
	},
	{
		"_id": 5,
		"nom": "Biología",
		"des": "Estudio de organismos vivos",
		"estudios": ["5"]
	}
])


// Create telefono

db.telefono.insertMany([
	{
		"_id": "1",
		"oper": "Movistar",
		"primaryDuenio": 123456789
	},
	{
		"_id": "2",
		"oper": "Claro",
		"primaryDuenio": 987654321
	},
	{
		"_id": "3",
		"oper": "Tigo",
		"primaryDuenio": 321654987
	},
	{
		"_id": "4",
		"oper": "Movistar",
		"primaryDuenio": 147258369
	},
	{
		"_id": "5",
		"oper": "Claro",
		"primaryDuenio": 963852741
	}
])

// Create estudios

db.estudios.insertMany([
	{
		"_id": "1",
		"primaryPersona": 123456789,
		"primaryProfesion": 1,
		"fecha": "2023-01-01",
		"univer": "Universidad Nacional"
	},
	{
		"_id": "2",
		"primaryPersona": 987654321,
		"primaryProfesion": 2,
		"fecha": "2022-05-01",
		"univer": "Universidad de Los Andes"
	},
	{
		"_id": "3",
		"primaryPersona": 321654987,
		"primaryProfesion": 3,
		"fecha": "2021-08-01",
		"univer": "Universidad Javeriana"
	},
	{
		"_id": "4",
		"primaryPersona": 147258369,
		"primaryProfesion": 4,
		"fecha": "2020-02-01",
		"univer": "Universidad de Antioquia"
	},
	{
		"_id": "5",
		"primaryPersona": 963852741,
		"primaryProfesion": 5,
		"fecha": "2019-09-01",
		"univer": "Universidad del Valle"
	}
])

db.persona.aggregate([
	{
		$lookup: {
			from: "telefono",
			localField: "telefonos",
			foreignField: "_id",
			as: "telefonos"
		}
	}
])

