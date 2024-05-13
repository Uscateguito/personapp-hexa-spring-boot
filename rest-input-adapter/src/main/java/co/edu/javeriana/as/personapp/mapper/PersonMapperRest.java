package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.model.request.PersonRequest;
import co.edu.javeriana.as.personapp.model.response.PersonResponse;

@Mapper
public class PersonMapperRest {
	
	public PersonResponse fromDomainToAdapterRestMaria(Person person) {
		return fromDomainToAdapterRest(person, "MariaDB");
	}
	public PersonResponse fromDomainToAdapterRestMongo(Person person) {
		return fromDomainToAdapterRest(person, "MongoDB");
	}
	
	public PersonResponse fromDomainToAdapterRest(Person person, String database) {
		return new PersonResponse(
				person.getIdentification()+"", 
				person.getFirstName(), 
				person.getLastName(), 
				person.getAge()+"", 
				person.getGender().toString(),
				database,
				"OK");
	}

	public Person fromAdapterToDomain(PersonRequest request) {
//		IMPLEMENTAR
		try {
			return Person.builder()
					.identification(Integer.parseInt(request.getDni()))
					.firstName(request.getFirstName())
					.lastName(request.getLastName())
					.age(Integer.parseInt(request.getAge()))
					.gender(Gender.valueOf(request.getSex()))
					.build();
		}
		catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
		
}
