package co.edu.javeriana.as.personapp.terminal.adapter;

import co.edu.javeriana.as.personapp.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.terminal.mapper.PersonMapperCLI;
import co.edu.javeriana.as.personapp.terminal.model.PersonModelCLI;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class PersonInputAdapterCLI {

	@Autowired
	@Qualifier("personOutputAdapterMaria")
	private PersonOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("personOutputAdapterMongo")
	private PersonOutputPort personOutputPortMongo;

	@Autowired
	private PersonMapperCLI personMapperCli;

	PersonInputPort personInputPort;

	public String setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMaria);
			return DatabaseOption.MARIA.toString();
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			personInputPort = new PersonUseCase(personOutputPortMongo);
			return  DatabaseOption.MONGO.toString();
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public List<PersonModelCLI> getAll(String database) {
		try {
			if(setPersonOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return personInputPort.findAll().stream().map(personMapperCli::fromDomainToAdapterRestMaria)
						.collect(Collectors.toList());
			}else {
				return personInputPort.findAll().stream().map(personMapperCli::fromDomainToAdapterRestMongo)
						.collect(Collectors.toList());
			}

		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<PersonModelCLI>();
		}
	}

	public PersonModelCLI create(PersonModelCLI persona, String dbOption) {
		log.info("Into crear PersonEntity in Input Adapter");
		try {
			if(setPersonOutputPortInjection(persona.getDb()).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				log.info("Into crear PersonaEntity in PersonaInputAdapterRest with MariaDB");
				return personMapperCli.fromDomainToAdapterRestMaria(personInputPort.create(personMapperCli.fromAdapterCliToDomain(persona)));
			}else {
				return personMapperCli.fromDomainToAdapterRestMongo(personInputPort.create(personMapperCli.fromAdapterCliToDomain(persona)));
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			System.out.println("Error");
		}
		return null;
	}

	public PersonModelCLI edit(PersonModelCLI persona, String dbOption) {
		log.info("Into editar PersonEntity in Input Adapter");
		try {
			if (setPersonOutputPortInjection(persona.getDb()).equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
				return personMapperCli.fromDomainToAdapterRestMaria(personInputPort.edit(persona.getCc(), personMapperCli.fromAdapterCliToDomain(persona)));
			} else {
				return personMapperCli.fromDomainToAdapterRestMongo(personInputPort.edit(persona.getCc(), personMapperCli.fromAdapterCliToDomain(persona)));
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			System.out.println("Error");
		}
		return null;
	}

	public boolean delete(int cc, String dbOption) {
		log.info("Into eliminar PersonEntity in Input Adapter");
		try {
			if(setPersonOutputPortInjection(dbOption).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return personInputPort.drop(cc);
			}else {
				return personInputPort.drop(cc);
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			System.out.println("Error");
		}
		return false;
	}

	public PersonModelCLI getById(int cc, String database) {
		log.info("Into buscar PersonEntity in Input Adapter");
		try {
			if (setPersonOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
				return personMapperCli.fromDomainToAdapterRestMaria(personInputPort.findOne(cc));
			} else {
				return personMapperCli.fromDomainToAdapterRestMongo(personInputPort.findOne(cc));
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			System.out.println("Error");
		}

		return null;
	}

	public Long count(String db) {
		log.info("Into contar PersonEntity in Input Adapter");
		try {
			if(setPersonOutputPortInjection(db).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return personInputPort.count();
			}else {
				return personInputPort.count();
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			System.out.println("Error");
		}
		return null;
	}
}