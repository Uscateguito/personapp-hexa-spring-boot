package co.edu.javeriana.as.personapp.mongo.adapter;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mongo.document.EstudiosDocument;
import co.edu.javeriana.as.personapp.mongo.mapper.EstudiosMapperMongo;
import co.edu.javeriana.as.personapp.mongo.repository.EstudiosRepositoryMongo;
import com.mongodb.MongoWriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("StudyOutputAdapterMongo")
public class StudyOutputAdapterMongo implements StudyOutputPort {

    @Autowired
    private EstudiosRepositoryMongo estudiosRepositoryMongo;

    @Autowired
    private EstudiosMapperMongo estudiosMapperMongo;

    @Override
    public Study save(Study study) {
        log.info("Into save on Adapter MongoDB");
        try {
            EstudiosDocument persistedEstudios = estudiosRepositoryMongo.save(estudiosMapperMongo.fromDomainToAdapter(study));
            return estudiosMapperMongo.fromAdapterToDomain(persistedEstudios);
        } catch (MongoWriteException e) {
            log.warn(e.getMessage());
            return study;
        }
    }

    @Override
    public Boolean delete(Integer identification) {
//        TODO Auto-generated method stub
        return null;
    }

//    @Override
//    public Boolean delete(String identification) {
//        log.info("Into delete on Adapter MongoDB");
//        estudiosRepositoryMongo.deleteById(identification);
//        return estudiosRepositoryMongo.findById(identification).isEmpty();
//    }

    @Override
    public List<Study> find() {
        log.info("Into find on StudyOutputAdapterMongo");
        log.info("Studies: " + estudiosRepositoryMongo.findAll().toString());
        return estudiosRepositoryMongo.findAll().stream().map(estudiosMapperMongo::fromAdapterToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Study findById(Integer professionId, Integer personId) {
        log.info("Into findById on Adapter MongoDB");
        if (estudiosRepositoryMongo.findByPrimaryProfesionAndPrimaryPersona(professionId, personId) == null) {
            return null;
        } else {
            return estudiosMapperMongo.fromAdapterToDomain(
                    estudiosRepositoryMongo.findByPrimaryProfesionAndPrimaryPersona(professionId, personId)
            );
        }
    }


//    @Override
//    public Study findById(String identification) {
//        log.info("Into findById on Adapter MongoDB");
//        if (estudiosRepositoryMongo.findById(identification).isEmpty()) {
//            return null;
//        } else {
//            return estudiosMapperMongo.fromAdapterToDomain(estudiosRepositoryMongo.findById(identification).get());
//        }
//    }

    @Override
    public Long count() {
        return estudiosRepositoryMongo.count();
    }

}
