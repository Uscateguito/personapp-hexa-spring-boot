package co.edu.javeriana.as.personapp.mariadb.adapter;


import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mariadb.entity.TelefonoEntity;
import co.edu.javeriana.as.personapp.mariadb.mapper.TelefonoMapperMaria;
import co.edu.javeriana.as.personapp.mariadb.repository.TelefonoRepositoryMaria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("TelefonoOutputAdapterMaria")
@Transactional
public class TelefonoOutputAdapterMaria {

    @Autowired
    private TelefonoRepositoryMaria telefonoRepositoryMaria;

    @Autowired
    private TelefonoMapperMaria telefonoMapperMaria;

    public Phone save(Phone telefono) {
        log.debug("Into save on Adapter MariaDB");
        TelefonoEntity persistedTelefono = telefonoRepositoryMaria.save(telefonoMapperMaria.fromDomainToAdapter(telefono));
        return telefonoMapperMaria.fromAdapterToDomain(persistedTelefono);
    }

    public Boolean delete(String id) {
        log.debug("Into delete on Adapter MariaDB");
        telefonoRepositoryMaria.deleteById(id);
        return telefonoRepositoryMaria.findById(id).isEmpty();
    }

    public Phone findById(String id) {
        log.debug("Into findById on Adapter MariaDB");
        if (telefonoRepositoryMaria.findById(id).isEmpty
                ()) {
            return null;
        } else {
            return telefonoMapperMaria.fromAdapterToDomain(telefonoRepositoryMaria.findById(id).get());
        }
    }

    public List<Phone> find() {
        log.debug("Into find on Adapter MariaDB");
        return telefonoRepositoryMaria.findAll().stream().map(telefonoMapperMaria::fromAdapterToDomain).collect(Collectors.toList());
    }


}
