package co.edu.javeriana.as.personapp.terminal.adapter;

import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.terminal.mapper.ProfessionMapperCLI;
import co.edu.javeriana.as.personapp.terminal.model.ProfessionModelCLI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class ProfessionInputAdapterCLI {

    @Autowired
    @Qualifier("ProfessionOutputAdapterMaria")
    private ProfessionOutputPort professionOutputPortMaria;

    @Autowired
    @Qualifier("ProfessionOutputAdapterMongo")
    private ProfessionOutputPort professionOutputPortMongo;

    @Autowired
    private ProfessionMapperCLI professionMapperCLI;

    ProfessionOutputPort professionOutputPort;

    public String setProfessionOutputPortInjection(String dbOption) {
        if (dbOption.equalsIgnoreCase("maria")) {
            professionOutputPort = professionOutputPortMaria;
            return "maria";
        } else if (dbOption.equalsIgnoreCase("mongo")) {
            professionOutputPort = professionOutputPortMongo;
            return "mongo";
        } else {
            return null;
        }
    }

    public List<ProfessionModelCLI> getAll(String database) {
        log.info("Into getAll ProfessionEntity in Input Adapter");
        try {
            if(setProfessionOutputPortInjection(database).equalsIgnoreCase("maria")){
                return professionOutputPort.find().stream().map(professionMapperCLI::fromDomainToAdapterMaria).collect(Collectors.toList());
            }else {
                return professionOutputPort.find().stream().map(professionMapperCLI::fromDomainToAdapterMongo).collect(Collectors.toList());
            }
        } catch (Exception e) {
            return null;
        }
    }

    public ProfessionModelCLI getById(Integer id, String database) {
        log.info("Into findById ProfessionEntity in Input Adapter");
        try {
            if(setProfessionOutputPortInjection(database).equalsIgnoreCase("maria")){
                return professionMapperCLI.fromDomainToAdapterMaria(professionOutputPort.findById(id));
            }else {
                return professionMapperCLI.fromDomainToAdapterMongo(professionOutputPort.findById(id));
            }
        } catch (Exception e) {
            return null;
        }
    }

    public ProfessionModelCLI create(ProfessionModelCLI profession, String database) {
        log.info("Into save ProfessionEntity in Input Adapter");
        try {
            if(setProfessionOutputPortInjection(database).equalsIgnoreCase("maria")){
                return professionMapperCLI.fromDomainToAdapterMaria(professionOutputPort.save(professionMapperCLI.fromAdapterCliToDomain(profession)));
            }else {
                return professionMapperCLI.fromDomainToAdapterMongo(professionOutputPort.save(professionMapperCLI.fromAdapterCliToDomain(profession)));
            }
        } catch (Exception e) {
            return null;
        }
    }

    public ProfessionModelCLI update(ProfessionModelCLI profession, String database) {
        log.info("Into update ProfessionEntity in Input Adapter");
        try {
            if(setProfessionOutputPortInjection(database).equalsIgnoreCase("maria")){
                return professionMapperCLI.fromDomainToAdapterMaria(professionOutputPort.save(professionMapperCLI.fromAdapterCliToDomain(profession)));
            }else {
                return professionMapperCLI.fromDomainToAdapterMongo(professionOutputPort.save(professionMapperCLI.fromAdapterCliToDomain(profession)));
            }
        } catch (Exception e) {
            return null;
        }
    }

    public ProfessionModelCLI edit(ProfessionModelCLI profession, String database) {
        log.info("Into edit ProfessionEntity in Input Adapter");
        try {
            if(setProfessionOutputPortInjection(database).equalsIgnoreCase("maria")){
                return professionMapperCLI.fromDomainToAdapterMaria(professionOutputPort.save(professionMapperCLI.fromAdapterCliToDomain(profession)));
            }else {
                return professionMapperCLI.fromDomainToAdapterMongo(professionOutputPort.save(professionMapperCLI.fromAdapterCliToDomain(profession)));
            }
        } catch (Exception e) {
            return null;
        }
    }


    public boolean delete(Integer id, String database) {
        log.info("Into delete ProfessionEntity in Input Adapter");
        try {
            if(setProfessionOutputPortInjection(database).equalsIgnoreCase("maria")){
                return professionOutputPort.delete(id);
            }else {
                return professionOutputPort.delete(id);
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Long count(String db) {
        log.info("Into count ProfessionEntity in Input Adapter");
        try {
            if(setProfessionOutputPortInjection(db).equalsIgnoreCase("maria")){
                return professionOutputPort.count();
            }else {
                return professionOutputPort.count();
            }
        } catch (Exception e) {
            return 0L;
        }
    }
}
