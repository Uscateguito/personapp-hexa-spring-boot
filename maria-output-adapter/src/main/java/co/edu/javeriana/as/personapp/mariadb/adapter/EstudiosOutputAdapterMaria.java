package co.edu.javeriana.as.personapp.mariadb.adapter;

import co.edu.javeriana.as.personapp.application.port.out.StudiesOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.mapper.EstudiosMapperMaria;
import co.edu.javeriana.as.personapp.mariadb.repository.EstudiosRepositoryMaria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("EstudiosOutputAdapterMaria")
@Transactional
public class EstudiosOutputAdapterMaria implements StudiesOutputPort {

    @Autowired
    private EstudiosRepositoryMaria estudiosRepositoryMaria;

    @Autowired
    private EstudiosMapperMaria estudiosMapperMaria;

    public Study save(Study estudio) {
        log.debug("Into save on Adapter MariaDB");
        return estudiosMapperMaria.fromAdapterToDomain(estudiosRepositoryMaria.save(estudiosMapperMaria.fromDomainToAdapter(estudio)));
    }

    public Boolean delete(Integer id) {
        log.debug("Into delete on Adapter MariaDB");
        estudiosRepositoryMaria.deleteById(id);
        return estudiosRepositoryMaria.findById(id).isEmpty();
    }

    public Study findById(Integer id) {
        log.debug("Into findById on Adapter MariaDB");
        if (estudiosRepositoryMaria.findById(id).isEmpty()) {
            return null;
        } else {
            return estudiosMapperMaria.fromAdapterToDomain(estudiosRepositoryMaria.findById(id).get());
        }
    }

    @Override
    public Long count() {
        return null;
    }

    public List<Study> find() {
        log.debug("Into find on Adapter MariaDB");
        return estudiosRepositoryMaria.findAll().stream().map(estudiosMapperMaria::fromAdapterToDomain).collect(Collectors.toList());
    }

}
