package co.edu.javeriana.as.personapp.application.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
public class PersonUseCase implements PersonInputPort {

	private PersonOutputPort personPersintence;

//	Qualifier es una etiqueta que sirve para identificar un bean en el contenedor de Spring.
//	Por ejemplo en este caso se esta inyectando un bean de tipo PersonOutputPort con el nombre "personOutputAdapterMaria" en el constructor de la clase PersonUseCase.
//	El Qualifier en este caso particular hace de filtro para que Spring inyecte el bean correcto en el constructor.
//	Sin embargo, si se inyecta alguna
	public PersonUseCase(@Qualifier("personOutputAdapterMaria") PersonOutputPort personPersintence) {
		this.personPersintence=personPersintence;
	}
	
	@Override
	public void setPersintence(PersonOutputPort personPersintence) {
		this.personPersintence=personPersintence;
	}

	@Override
	public Person create(Person person) {
		log.debug("Into create on Application Domain");
		return personPersintence.save(person);
	}

	@Override
	public Person edit(Integer identification, Person person) throws NoExistException {
		Person oldPerson = personPersintence.findById(identification);
		if (oldPerson != null)
			return personPersintence.save(person);
		throw new NoExistException(
				"The person with id " + identification + " does not exist into db, cannot be edited");
	}

	@Override
	public Boolean drop(Integer identification) throws NoExistException {
		log.info("Into drop in PersonUseCase using PersonOutputPort");
		Person oldPerson = personPersintence.findById(identification);
		if (oldPerson != null)
			return personPersintence.delete(identification);
		throw new NoExistException(
				"The person with id " + identification + " does not exist into db, cannot be dropped");
	}

	@Override
	public List<Person> findAll() {
		log.info("Into findAll in PersonUseCase using PersonOutputPort");
		return personPersintence.find();
	}

	@Override
	public Person findOne(Integer identification) throws NoExistException {
		log.info("Into findOne in PersonUserCase using PersonOutputPort receiving" + personPersintence.findById(identification));
		Person oldPerson = personPersintence.findById(identification);
		if (oldPerson != null)
			return oldPerson;
		throw new NoExistException("The person with id " + identification + " does not exist into db, cannot be found");
	}

	@Override
	public Long count() {
		log.info("Into count in PersonUseCase");
		return personPersintence.count();
	}

	@Override
	public List<Phone> getPhones(Integer identification) throws NoExistException {
		log.info("Into getPhones in Person Use Case using PersonOutputPort");
		Person oldPerson = personPersintence.findById(identification);
		if (oldPerson != null)
			return oldPerson.getPhoneNumbers();
		throw new NoExistException(
				"The person with id " + identification + " does not exist into db, cannot be getting phones");
	}

	@Override
	public List<Study> getStudies(Integer identification) throws NoExistException {
		Person oldPerson = personPersintence.findById(identification);
		if (oldPerson != null)
			return oldPerson.getStudies();
		throw new NoExistException(
				"The person with id " + identification + " does not exist into db, cannot be getting studies");
	}
}
