package co.edu.javeriana.as.personapp.terminal.adapter;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.terminal.mapper.StudyMapperCLI;
import co.edu.javeriana.as.personapp.terminal.model.StudyModelCLI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class StudyInputAdapterCLI {

    @Autowired
    @Qualifier("StudyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("StudyOutputAdapterMongo")
    private StudyOutputPort studyOutputPortMongo;

    @Autowired
    private StudyMapperCLI studyMapperCLI;

    StudyOutputPort studyOutputPort;

    public String setStudyOutputPortInjection(String dbOption) {
        if (dbOption.equalsIgnoreCase("maria")) {
            studyOutputPort = studyOutputPortMaria;
            return "maria";
        } else if (dbOption.equalsIgnoreCase("mongo")) {
            studyOutputPort = studyOutputPortMongo;
            return "mongo";
        } else {
            return null;
        }
    }

    public List<StudyModelCLI> getAll(String database) {
        log.info("Into getAll StudyEntity in Input Adapter");
        try {
            if(setStudyOutputPortInjection(database).equalsIgnoreCase("maria")){
                return studyOutputPort.find().stream().map(studyMapperCLI::fromDomainToAdapterMaria).collect(Collectors.toList());
            }else {
                return studyOutputPort.find().stream().map(studyMapperCLI::fromDomainToAdapterMongo).collect(Collectors.toList());
            }
        } catch (Exception e) {
            return null;
        }
    }

    public StudyModelCLI getById(Integer profesion_id, Integer persona_id,  String database) {
        log.info("Into findById StudyEntity in Input Adapter");
        try {
            if(setStudyOutputPortInjection(database).equalsIgnoreCase("maria")){
                return studyMapperCLI.fromDomainToAdapterMaria(studyOutputPort.findById(profesion_id, persona_id));
            }else {
                return studyMapperCLI.fromDomainToAdapterMongo(studyOutputPort.findById(profesion_id, persona_id));
            }
        } catch (Exception e) {
            return null;
        }
    }

    public StudyModelCLI create(StudyModelCLI study, String database) {
        log.info("Into save StudyEntity in Input Adapter");
        try {
            if(setStudyOutputPortInjection(database).equalsIgnoreCase("maria")){
                return studyMapperCLI.fromDomainToAdapterMaria(studyOutputPort.save(studyMapperCLI.fromAdapterCliToDomain(study)));
            }else {
                return studyMapperCLI.fromDomainToAdapterMongo(studyOutputPort.save(studyMapperCLI.fromAdapterCliToDomain(study)));
            }
        } catch (Exception e) {
            return null;
        }
    }

//    public StudyModelCLI delete(Integer id, String database) {
//        log.info("Into delete StudyEntity in Input Adapter");
//        try {
//            if(setStudyOutputPortInjection(database).equalsIgnoreCase("maria")){
//                return studyMapperCLI.fromDomainToAdapterMaria(studyOutputPort.delete(id));
//            }else {
//                return studyMapperCLI.fromDomainToAdapterMongo(studyOutputPort.delete(id));
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }

    public StudyModelCLI update(StudyModelCLI study, String database) {
        log.info("Into update StudyEntity in Input Adapter");
        try {
            if(setStudyOutputPortInjection(database).equalsIgnoreCase("maria")){
                return studyMapperCLI.fromDomainToAdapterMaria(studyOutputPort.save(studyMapperCLI.fromAdapterCliToDomain(study)));
            }else {
                return studyMapperCLI.fromDomainToAdapterMongo(studyOutputPort.save(studyMapperCLI.fromAdapterCliToDomain(study)));
            }
        } catch (Exception e) {
            return null;
        }
    }


    public String count(String db) {
        log.info("Into count StudyEntity in Input Adapter");
        try {
            if (setStudyOutputPortInjection(db).equalsIgnoreCase("maria")) {
                return String.valueOf(studyOutputPort.count());
            } else {
                return String.valueOf(studyOutputPort.count());
            }
        } catch (Exception e) {
            return null;
        }
    }

}
