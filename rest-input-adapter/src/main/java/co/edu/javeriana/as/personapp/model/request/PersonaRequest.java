package co.edu.javeriana.as.personapp.model.request;

import co.edu.javeriana.as.personapp.domain.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonaRequest {
	private String dni;
	private String firstName;
	private String lastName;
	private String age;
	private String sex;
	private List<Phone> number;
	private String database;
}
