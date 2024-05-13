package co.edu.javeriana.as.personapp.mariadb.adapter;

import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.mariadb.entity.ProfesionEntity;
import co.edu.javeriana.as.personapp.mariadb.mapper.ProfesionMapperMaria;
import co.edu.javeriana.as.personapp.mariadb.repository.ProfesionRepositoryMaria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("ProfesionOutputAdapterMaria")
@Transactional
public class ProfesionOutputAdapterMaria {

    @Autowired
    private ProfesionRepositoryMaria profesionRepositoryMaria;

    @Autowired
    private ProfesionMapperMaria profesionMapperMaria;

    public Profession save(Profession profesion) {
        log.debug("Into save on Adapter MariaDB");
        ProfesionEntity persistedProfesion = profesionRepositoryMaria.save(profesionMapperMaria.fromDomainToAdapter(profesion));
        return profesionMapperMaria.fromAdapterToDomain(persistedProfesion);
    }

    public Boolean delete(Integer id) {
        log.debug("Into delete on Adapter MariaDB");
        profesionRepositoryMaria.deleteById(id);
        return profesionRepositoryMaria.findById(id).isEmpty();
    }

    public Profession findById(Integer id) {
        log.debug("Into findById on Adapter MariaDB");
        if (profesionRepositoryMaria.findById(id).isEmpty()) {
            return null;
        } else {
            return profesionMapperMaria.fromAdapterToDomain(profesionRepositoryMaria.findById(id).get());
        }
    }

    public List<Profession> find() {
        log.debug("Into find on Adapter MariaDB");
        return profesionRepositoryMaria.findAll().stream().map(profesionMapperMaria::fromAdapterToDomain).collect(Collectors.toList());
    }

}
