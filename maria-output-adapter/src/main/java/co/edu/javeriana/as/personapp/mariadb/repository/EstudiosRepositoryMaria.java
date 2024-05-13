package co.edu.javeriana.as.personapp.mariadb.repository;

import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudiosRepositoryMaria extends JpaRepository<EstudiosEntity, Integer> {

    EstudiosEntity findFirstByEstudiosEntityPK(EstudiosEntityPK estudiosEntityPK);
}
