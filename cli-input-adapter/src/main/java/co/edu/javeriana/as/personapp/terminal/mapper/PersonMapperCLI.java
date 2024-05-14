package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.terminal.model.PersonModelCLI;

@Mapper
public class PersonMapperCLI {

	public PersonModelCLI fromDomainToAdapterRestMaria(Person person) {
		return fromDomainToAdapterCli(person, "MariaDB");
	}
	public PersonModelCLI fromDomainToAdapterRestMongo(Person person) {
		return fromDomainToAdapterCli(person, "MongoDB");
	}

	public PersonModelCLI fromDomainToAdapterCli(Person person, String database) {
		return new PersonModelCLI(
				person.getIdentification(),
				person.getFirstName(),
				person.getLastName(),
				person.getGender().toString(),
				person.getAge(),
				database
		);
	}

	public Person fromAdapterCliToDomain(PersonModelCLI personModelCli) {
		return new Person(
				personModelCli.getCc(),
				personModelCli.getNombre(),
				personModelCli.getApellido(),
				defineGender(personModelCli.getGenero())
		);
	}

	private Gender defineGender(String genero)
	{
		if(genero.equalsIgnoreCase("M"))
			return Gender.MALE;
		else if (genero.equalsIgnoreCase("F"))
			return Gender.FEMALE;
		else
			return Gender.OTHER;
	}
}
