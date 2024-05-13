package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mapper.PersonaMapperRest;
import co.edu.javeriana.as.personapp.mapper.PhoneMapperRest;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.request.PhoneRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import co.edu.javeriana.as.personapp.model.response.PhoneResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class TelefonoInputAdapterRest {

    @Autowired
    @Qualifier("PhoneOutputAdapterMaria")
    private PhoneOutputPort PhoneOutputPortMaria;

    @Autowired
    @Qualifier("PhoneOutputAdapterMongo")
    private PhoneOutputPort PhoneOutputPortMongo;

    @Autowired
    private PhoneMapperRest phoneMapperRest;

    PhoneInputPort phoneInputPort;

    private String setPhoneOutputPortInjection(String dbOption) {
        if (dbOption.equalsIgnoreCase("MARIA")) {
            phoneInputPort = new PhoneUseCase(PhoneOutputPortMaria);
            return "MARIA";
        } else if (dbOption.equalsIgnoreCase("MONGO")) {
            phoneInputPort = new PhoneUseCase(PhoneOutputPortMongo);
            return "MONGO";
        } else {
            return null;
        }
    }


    public List<PhoneResponse> historial(String upperCase) {
        if(setPhoneOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into getPhones in TelefonoInputAdapterRest with MariaDB");
            return phoneInputPort.findAll().stream().map(phoneMapperRest::fromDomainToAdapterRestMaria)
                    .collect(Collectors.toList());
        }else {
            return phoneInputPort.findAll().stream().map(phoneMapperRest::fromDomainToAdapterRestMongo)
                    .collect(Collectors.toList());
        }

    }

    public PhoneResponse crearTelefono(PhoneRequest request) {
        setPhoneOutputPortInjection(request.getDatabase());
        Phone phone = phoneInputPort.create(phoneMapperRest.fromAdapterToDomain(request));
        return phoneMapperRest.fromDomainToAdapterRestMaria(phone);
    }

    public long count(String upperCase) {
        if(setPhoneOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into count TelefonoEntity in TelefonoInputAdapterRest with MariaDB");
            return phoneInputPort.count();
        }else {
            return phoneInputPort.count();
        }
    }

    public PhoneResponse getByNumber(String numero, String upperCase) {
        try {
            if(setPhoneOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                log.info("Into getTelefono in TelefonoInputAdapterRest with MariaDB");
                return phoneMapperRest.fromDomainToAdapterRestMaria(phoneInputPort.findOne(numero));
            }else {
                return phoneMapperRest.fromDomainToAdapterRestMongo(phoneInputPort.findOne(numero));
            }
        } catch ( NoExistException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

//    public PhoneResponse borrarTelefono(String id, String upperCase) {
//        try {
//            if(setPhoneOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
//                log.info("Into borrarTelefono in TelefonoInputAdapterRest with MariaDB");
//                return phoneMapperRest.fromDomainToAdapterRestMaria(phoneInputPort.delete(id));
//            }else {
//                return phoneMapperRest.fromDomainToAdapterRestMongo(phoneInputPort.delete(id));
//            }
//        } catch (InvalidOptionException e) {
//            log.warn(e.getMessage());
//            return null;
//        }
//    }

    public PhoneResponse actualizarTelefono(String number, PhoneRequest request, String upperCase) {
        try {
            if (setPhoneOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
                log.info("Into actualizarTelefono in TelefonoInputAdapterRest with MariaDB");
                return phoneMapperRest.fromDomainToAdapterRestMaria(phoneInputPort.edit(number, phoneMapperRest.fromAdapterToDomain(request)));
            } else {
                return phoneMapperRest.fromDomainToAdapterRestMongo(phoneInputPort.edit(number, phoneMapperRest.fromAdapterToDomain(request)));
            }
        } catch (NoExistException e) {
            throw new RuntimeException(e);
        }
    }



}
