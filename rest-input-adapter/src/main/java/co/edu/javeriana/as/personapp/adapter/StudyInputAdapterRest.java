package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mapper.StudyMapperRest;
import co.edu.javeriana.as.personapp.model.request.StudyRequest;
import co.edu.javeriana.as.personapp.model.response.StudyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class StudyInputAdapterRest {

    @Autowired
    @Qualifier("StudyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("StudyOutputAdapterMongo")
    private StudyOutputPort studyOutputPortMongo;

    @Autowired
    private StudyMapperRest studyMapperRest;

    StudyInputPort studyInputPort;

    private String setStudyOutputPortInjection(String dbOption) {
        if (dbOption.equalsIgnoreCase("MARIA")) {
            studyInputPort = new StudyUseCase(studyOutputPortMaria);
            return "MARIA";
        } else if (dbOption.equalsIgnoreCase("MONGO")) {
            studyInputPort = new StudyUseCase(studyOutputPortMongo);
            return "MONGO";
        } else {
            return null;
        }
    }

    public List<StudyResponse> historial(String database) {
        if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into getStudies in StudyInputAdapterRest with MariaDB");
            log.info("Studies: " + studyInputPort.findAll().toString());
            return studyInputPort.findAll().stream().map(studyMapperRest::fromDomainToAdapterRestMaria)
                    .collect(Collectors.toList());
        }else {
            log.info("Into getStudies in StudyInputAdapterRest with MongoDB");
//            log.info("Studies: " + studyInputPort.findAll().toString());
            return studyInputPort.findAll().stream().map(study -> studyMapperRest.fromDomainToAdapterRestMongo(study))
                    .collect(Collectors.toList());
        }
    }

    public StudyResponse save(StudyRequest request, String database) {
        if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into save in StudyInputAdapterRest with MariaDB");
            Study study = studyInputPort.create(studyMapperRest.fromAdapterToDomain(request));
            return studyMapperRest.fromDomainToAdapterRestMaria(study);
        }else {
            log.info("Into save in StudyInputAdapterRest with MongoDB");
            Study study = studyInputPort.create(studyMapperRest.fromAdapterToDomain(request));
            return studyMapperRest.fromDomainToAdapterRestMongo(study);
        }
    }

    public long count(String database) {
        if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into count StudyEntity in StudyInputAdapterRest with MariaDB");
            return studyInputPort.count();
        }else {
            log.info("Into count StudyEntity in StudyInputAdapterRest with MongoDB");
            return studyInputPort.count();
        }
    }

    public StudyResponse getById(Integer professionId, Integer personId, String database) throws NoExistException {
        if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into findById in StudyInputAdapterRest with MariaDB");
            return studyMapperRest.fromDomainToAdapterRestMaria(studyInputPort.findOne(professionId, personId));
        }else {
            log.info("Into findById in StudyInputAdapterRest with MongoDB");
            return studyMapperRest.fromDomainToAdapterRestMongo(studyInputPort.findOne(professionId, personId));
        }
    }

    public StudyResponse edit(Integer professionId, Integer personId, StudyRequest request, String database) throws NoExistException {
        if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
            log.info("Into edit in StudyInputAdapterRest with MariaDB");
            Study study = studyInputPort.edit(professionId, personId, studyMapperRest.fromAdapterToDomain(request));
            return studyMapperRest.fromDomainToAdapterRestMaria(study);
        }else {
            log.info("Into edit in StudyInputAdapterRest with MongoDB");
            Study study = studyInputPort.edit(professionId, personId, studyMapperRest.fromAdapterToDomain(request));
            return studyMapperRest.fromDomainToAdapterRestMongo(study);
        }
    }


}
