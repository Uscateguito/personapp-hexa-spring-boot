package co.edu.javeriana.as.personapp.terminal.adapter;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.mapper.PhoneMapperCLI;
import co.edu.javeriana.as.personapp.terminal.model.PersonModelCLI;
import co.edu.javeriana.as.personapp.terminal.model.PhoneModelCLI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class PhoneInputAdapterCLI {

    @Autowired
    @Qualifier("PhoneOutputAdapterMaria")
    private PhoneOutputPort phoneOutputPortMaria;

    @Autowired
    @Qualifier("PhoneOutputAdapterMongo")
    private PhoneOutputPort phoneOutputPortMongo;

    @Autowired
    private PhoneMapperCLI phoneMapperCLI;

    PhoneOutputPort phoneOutputPort;

    public String setPhoneOutputPortInjection(String dbOption) {
        if (dbOption.equalsIgnoreCase("maria")) {
            phoneOutputPort = phoneOutputPortMaria;
            return "maria";
        } else if (dbOption.equalsIgnoreCase("mongo")) {
            phoneOutputPort = phoneOutputPortMongo;
            return "mongo";
        } else {
            return null;
        }
    }

    public List<PhoneModelCLI> getAll(String database) {
        log.info("Into getAll PhoneEntity in Input Adapter");
        try {
            if(setPhoneOutputPortInjection(database).equalsIgnoreCase("maria")){
                return phoneOutputPort.find().stream().map(phoneMapperCLI::fromDomainToAdapterMaria).collect(Collectors.toList());
            }else {
                return phoneOutputPort.find().stream().map(phoneMapperCLI::fromDomainToAdapterMongo).collect(Collectors.toList());
            }
        } catch (Exception e) {
            return null;
        }
    }

    public PhoneModelCLI create(PhoneModelCLI phone, String database) {
        log.info("Into save PhoneEntity in Input Adapter");
        try {
            if(setPhoneOutputPortInjection(database).equalsIgnoreCase("maria")){
                return phoneMapperCLI.fromDomainToAdapterMaria(phoneOutputPort.save(phoneMapperCLI.fromAdapterCliToDomain(phone)));
            }else {
                return phoneMapperCLI.fromDomainToAdapterMongo(phoneOutputPort.save(phoneMapperCLI.fromAdapterCliToDomain(phone)));
            }
        } catch (Exception e) {
            return null;
        }
    }

    public PhoneModelCLI getById(String id, String database) {
        log.info("Into findById PhoneEntity in Input Adapter");
        try {
            if(setPhoneOutputPortInjection(database).equalsIgnoreCase("maria")){
                return phoneMapperCLI.fromDomainToAdapterMaria(phoneOutputPort.findByNumber(id));
            }else {
                return phoneMapperCLI.fromDomainToAdapterMongo(phoneOutputPort.findByNumber(id));
            }
        } catch (Exception e) {
            return null;
        }
    }

//    public boolean delete(String id, String database) {
//        log.info("Into delete PhoneEntity in Input Adapter");
//        try {
//            if(setPhoneOutputPortInjection(database).equalsIgnoreCase("maria")){
//                return phoneOutputPort.delete(id);
//            }else {
//                return phoneOutputPort.delete(id);
//            }
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public PhoneModelCLI edit(PhoneModelCLI phone, String database) {
        log.info("Into edit PhoneEntity in Input Adapter");
        try {
            if(setPhoneOutputPortInjection(database).equalsIgnoreCase("maria")){
                return phoneMapperCLI.fromDomainToAdapterMaria(phoneOutputPort.save(phoneMapperCLI.fromAdapterCliToDomain(phone)));
            }else {
                return phoneMapperCLI.fromDomainToAdapterMongo(phoneOutputPort.save(phoneMapperCLI.fromAdapterCliToDomain(phone)));
            }
        } catch (Exception e) {
            return null;
        }
    }


    public long count(String db) {
        log.info("Into count PhoneEntity in Input Adapter");
        try {
            if(setPhoneOutputPortInjection(db).equalsIgnoreCase("maria")){
                return phoneOutputPort.count();
            }else {
                return phoneOutputPort.count();
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
