package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.adapter.PersonInputAdapterRest;
import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.model.request.PhoneRequest;
import co.edu.javeriana.as.personapp.model.response.PhoneResponse;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class PhoneMapperRest {

    @Autowired
    private PersonInputAdapterRest personInputAdapterRest;

    @Autowired
    private PersonMapperRest personMapperRest;

    public PhoneResponse fromDomainToAdapterRestMaria(Phone phone) {
        return fromDomainToAdapterRest(phone, "MariaDB");
    }

    public PhoneResponse fromDomainToAdapterRestMongo(Phone phone) {
        return fromDomainToAdapterRest(phone, "MongoDB");
    }

    public PhoneResponse fromDomainToAdapterRest(Phone phone, String database) {
        return new PhoneResponse(
                phone.getNumber(),
                phone.getCompany(),
                phone.getOwner().getIdentification().toString(),
                database,
                "OK"
        );
    }

    public Phone fromAdapterToDomain(PhoneRequest request) {
        try {
            return Phone.builder()
                    .number(request.getNumber())
                    .company(request.getCompany())
                    .owner(
                            personMapperRest.fromAdapterToDomain(personInputAdapterRest
                                    .getById(Integer.valueOf(request.getOwnerId()), request.getDatabase()))
                    )
                    .build();
        }
        catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
