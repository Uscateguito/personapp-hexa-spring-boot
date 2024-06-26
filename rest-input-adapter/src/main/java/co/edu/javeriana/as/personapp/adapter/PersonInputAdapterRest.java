package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.mapper.PersonMapperRest;
import co.edu.javeriana.as.personapp.model.request.PersonRequest;
import co.edu.javeriana.as.personapp.model.response.PersonResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class PersonInputAdapterRest {

	@Autowired
	@Qualifier("personOutputAdapterMaria")
	private PersonOutputPort personOutputPortMaria;

	@Autowired
	@Qualifier("personOutputAdapterMongo")
	private PersonOutputPort personOutputPortMongo;

	@Autowired
	private PersonMapperRest personMapperRest;

	PersonInputPort personInputPort;

	private String setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
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

	public List<PersonResponse> historial(String database) {
		log.info("Into historial PersonaEntity in Input Adapter");
		try {
			if(setPersonOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return personInputPort.findAll().stream().map(personMapperRest::fromDomainToAdapterRestMaria)
						.collect(Collectors.toList());
			}else {
				return personInputPort.findAll().stream().map(personMapperRest::fromDomainToAdapterRestMongo)
						.collect(Collectors.toList());
			}
			
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<PersonResponse>();
		}
	}

	public PersonResponse crearPersona(PersonRequest request) {
		try {
			if(setPersonOutputPortInjection(request.getDatabase()).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				log.info("Into crear PersonaEntity in PersonaInputAdapterRest with MariaDB");
				return personMapperRest.fromDomainToAdapterRestMaria(personInputPort.create(personMapperRest.fromAdapterToDomain(request)));
			}else {
				return personMapperRest.fromDomainToAdapterRestMongo(personInputPort.create(personMapperRest.fromAdapterToDomain(request)));
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public long count(String database) {
		try {
			if(setPersonOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				log.info("Into count PersonaEntity in PersonaInputAdapterRest with MariaDB");
				return personInputPort.count();
			}else {
				return personInputPort.count();
			}

		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
//			return new PersonaResponse("", "", "", "", "", "", "");
		}
        return -1;
    }

	public PersonResponse getById(Integer identification, String upperCase) {
		try {
			if(setPersonOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				log.info("Into getById in PersonaInputAdapterRest with MariaDB");
				return personMapperRest.fromDomainToAdapterRestMaria(personInputPort.findOne(identification));
			}else {
				return personMapperRest.fromDomainToAdapterRestMongo(personInputPort.findOne(identification));
			}
		} catch (InvalidOptionException e) {

			log.warn(e.getMessage());
			return null;

		} catch (NoExistException e) {

            throw new RuntimeException(e);
        }
    }

	public Boolean borrarPersona(Integer identification, String upperCase) {
		try {
			if(setPersonOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				log.info("Into borrarPersona PersonaEntity in PersonaInputAdapterRest with MariaDB");
				return personInputPort.drop(identification);
			}else {
				return personInputPort.drop(identification);
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return false;
		} catch (NoExistException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getPhones(Integer identification, String upperCase) {
		log.info("Into getPhones PersonaEntity in Input Adapter");
		try {
			if(setPersonOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return personInputPort.getPhones(identification).stream().map(phone -> phone.getNumber())
						.collect(Collectors.toList());
			}else {
				return personInputPort.getPhones(identification).stream().map(phone -> phone.getNumber())
						.collect(Collectors.toList());
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<String>();
		} catch (NoExistException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getStudies(Integer identification, String upperCase) {
		log.info("Into getStudies PersonaEntity in Input Adapter");
		try {
			if(setPersonOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return personInputPort.getStudies(identification).stream().map(study -> study.toString())
						.collect(Collectors.toList());
			}else {
				return personInputPort.getStudies(identification).stream().map(study -> study.toString())
						.collect(Collectors.toList());
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<String>();
		} catch (NoExistException e) {
			throw new RuntimeException(e);
		}
	}

	public PersonResponse editarPersona(Integer identification, PersonRequest request, String upperCase) {
		try {
			if(setPersonOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
				return personMapperRest.fromDomainToAdapterRestMaria(personInputPort.edit(identification, personMapperRest.fromAdapterToDomain(request)));
			}else {
				return personMapperRest.fromDomainToAdapterRestMongo(personInputPort.edit(identification, personMapperRest.fromAdapterToDomain(request)));
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return null;
		} catch (NoExistException e) {
			throw new RuntimeException(e);
		}
	}
}
