package co.edu.javeriana.as.personapp.model.request;

import co.edu.javeriana.as.personapp.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneRequest {

    private String number;
    private String company;
    private Person owner;
    private String database;

    public PhoneRequest(String number, String company, Person owner) {
        this.number = number;
        this.company = company;
        this.owner = owner;
    }
}
