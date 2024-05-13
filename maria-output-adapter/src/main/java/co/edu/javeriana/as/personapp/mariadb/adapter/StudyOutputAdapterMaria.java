package co.edu.javeriana.as.personapp.mariadb.adapter;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntityPK;
import co.edu.javeriana.as.personapp.mariadb.entity.ProfesionEntity;
import co.edu.javeriana.as.personapp.mariadb.mapper.EstudiosMapperMaria;
import co.edu.javeriana.as.personapp.mariadb.repository.EstudiosRepositoryMaria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("StudyOutputAdapterMaria")
@Transactional
public class StudyOutputAdapterMaria implements StudyOutputPort {

    @Autowired
    private EstudiosRepositoryMaria estudiosRepositoryMaria;

    @Autowired
    private EstudiosMapperMaria estudiosMapperMaria;

    public Study save(Study estudio) {
        log.info("Into save on StudyOutputAdapterMaria");
        EstudiosEntity persistedEstudio = estudiosRepositoryMaria.save(estudiosMapperMaria.fromDomainToAdapter(estudio));
        log.info("Estudio: " + persistedEstudio.getFecha());
        Study study = estudiosMapperMaria.fromAdapterToDomain(persistedEstudio);
        log.info("Study: " + study);
        return estudiosMapperMaria.fromAdapterToDomain(persistedEstudio);
    }

    public Boolean delete(Integer id) {
        log.info("Into delete on Adapter StudyOutputAdapterMaria");
        estudiosRepositoryMaria.deleteById(id);
        return estudiosRepositoryMaria.findById(id).isEmpty();
    }

    public Study findById(Integer professionId, Integer personId) {
        log.info("Into findById on StudyOutputAdapterMaria");
        EstudiosEntityPK estudiosEntityPK = new EstudiosEntityPK(professionId, personId);
        log.info("EstudiosEntity: " + estudiosRepositoryMaria.findFirstByEstudiosEntityPK(estudiosEntityPK).getFecha());
        if (estudiosRepositoryMaria.findFirstByEstudiosEntityPK(estudiosEntityPK) == null) {
            return null;
        } else {
            return estudiosMapperMaria.fromAdapterToDomain(estudiosRepositoryMaria.findFirstByEstudiosEntityPK(estudiosEntityPK));
        }
    }

    @Override
    public Long count() {
        return estudiosRepositoryMaria.count();
    }

    public List<Study> find() {
        log.info("Into find on StudyOutputAdapterMaria");
        return estudiosRepositoryMaria.findAll().stream().map(estudiosMapperMaria::fromAdapterToDomain).collect(Collectors.toList());
    }

}
