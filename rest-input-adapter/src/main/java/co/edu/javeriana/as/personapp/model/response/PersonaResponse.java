package co.edu.javeriana.as.personapp.model.response;

import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

public class PersonaResponse extends PersonaRequest{

	private String status;
	
	public PersonaResponse(String dni, String firstName, String lastName, String age, String sex, String database, List<Phone> number, String status) {
		super(dni, firstName, lastName, age, sex, number, database);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	

}
