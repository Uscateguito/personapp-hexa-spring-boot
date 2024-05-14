package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.adapter.PersonInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.model.PhoneModelCLI;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class PhoneMapperCLI {

    @Autowired
    private PersonMapperCLI personMapperCLI;

    @Autowired
    private PersonInputAdapterCLI personInputAdapterCLI;

    public PhoneModelCLI fromDomainToAdapterMaria(Phone phone) {
        return fromDomainToAdapterCli(phone, "MariaDB");
    }

    public PhoneModelCLI fromDomainToAdapterMongo(Phone phone) {
        return fromDomainToAdapterCli(phone, "MongoDB");
    }

    public PhoneModelCLI fromDomainToAdapterCli(Phone phone, String database) {
        return new PhoneModelCLI(
                phone.getNumber(),
                phone.getCompany(),
                phone.getOwner().getIdentification().toString(),
                database
        );
    }

    public Phone fromAdapterCliToDomain(PhoneModelCLI phoneModelCLI) {

        return Phone.builder()
                .number(phoneModelCLI.getNumber())
                .company(phoneModelCLI.getCompany())
                .owner(
                        personMapperCLI.fromAdapterCliToDomain(
                                personInputAdapterCLI
                                        .getById(Integer.valueOf(phoneModelCLI.getIdPerson()), phoneModelCLI.getDb())
                        )
                )
                .build();
    }
}