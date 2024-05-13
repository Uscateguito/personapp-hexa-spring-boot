package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.ProfessionUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.mapper.ProfessionMapperRest;
import co.edu.javeriana.as.personapp.model.request.PersonRequest;
import co.edu.javeriana.as.personapp.model.request.ProfessionRequest;
import co.edu.javeriana.as.personapp.model.response.PersonResponse;
import co.edu.javeriana.as.personapp.model.response.ProfessionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class ProfessionInputAdapterRest {

    @Autowired
    @Qualifier("ProfessionOutputAdapterMaria")
    private ProfessionOutputPort professionOutputPortMaria;

    @Autowired
    @Qualifier("ProfessionOutputAdapterMongo")
    private ProfessionOutputPort professionOutputPortMongo;

    @Autowired
    private ProfessionMapperRest professionMapperRest;

    ProfessionInputPort professionInputPort;

    private String setProfessionOutputPortInjection(String dbOption) {
        if (dbOption.equalsIgnoreCase("MARIA")) {
            professionInputPort = new ProfessionUseCase(professionOutputPortMaria);
            return "MARIA";
        } else if (dbOption.equalsIgnoreCase("MONGO")) {
            professionInputPort = new ProfessionUseCase(professionOutputPortMongo);
            return "MONGO";
        } else {
            return null;
        }
    }

    public List<ProfessionResponse> historial(String database) {
        if(setProfessionOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into getProfessions in ProfessionInputAdapterRest with MariaDB");
            return professionInputPort.findAll().stream().map(professionMapperRest::fromDomainToAdapterRestMaria)
                    .collect(Collectors.toList());
        }else {
            return professionInputPort.findAll().stream().map(professionMapperRest::fromDomainToAdapterRestMongo)
                    .collect(Collectors.toList());
        }
    }

    public ProfessionResponse crearProfesion(ProfessionRequest request, String database) {
        try{
            if(setProfessionOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                log.info("Into createProfession in ProfessionInputAdapterRest with MariaDB");
                Profession profession = professionInputPort.create(professionMapperRest.fromAdapterToDomain(request));
                return professionMapperRest.fromDomainToAdapterRestMaria(profession);

            }else {
                Profession profession = professionInputPort.create(professionMapperRest.fromAdapterToDomain(request));
                return professionMapperRest.fromDomainToAdapterRestMongo(profession);

            }
        }
        catch (Exception e){
            return null;
        }
    }

    public long count(String upperCase) {
        if(setProfessionOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into count ProfessionEntity in ProfessionInputAdapterRest with MariaDB");
            return professionInputPort.count();
        }else {
            return professionInputPort.count();
        }
    }

    public ProfessionResponse getById(Integer identification, String database) throws NoExistException {
        if(setProfessionOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into getProfession in ProfessionInputAdapterRest with MariaDB");
            return professionMapperRest.fromDomainToAdapterRestMaria(professionInputPort.findOne(identification));
        }else {
            return professionMapperRest.fromDomainToAdapterRestMongo(professionInputPort.findOne(identification));
        }
    }

    public Boolean eliminar(String upperCase, int id) throws NoExistException {
        if(setProfessionOutputPortInjection(upperCase).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into delete ProfessionEntity in ProfessionInputAdapterRest with MariaDB");
            return professionInputPort.drop(id);
        }else {
            return professionInputPort.drop(id);
        }
    }


    public ProfessionResponse actualizar(String database, int id, ProfessionRequest request) {
        try {
            if (setProfessionOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
                log.info("Into updateProfession in ProfessionInputAdapterRest with MariaDB");
                return professionMapperRest.fromDomainToAdapterRestMaria(professionInputPort.edit(id, professionMapperRest.fromAdapterToDomain(request)));
            } else {
                return professionMapperRest.fromDomainToAdapterRestMongo(professionInputPort.edit(id, professionMapperRest.fromAdapterToDomain(request)));
            }
        } catch (NoExistException e) {
            throw new RuntimeException(e);
        }
    }

}
