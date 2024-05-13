package co.edu.javeriana.as.personapp.mariadb.adapter;


import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
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
@Adapter("PhoneOutputAdapterMaria")
@Transactional
public class PhoneOutputAdapterMaria implements PhoneOutputPort {

    @Autowired
    private TelefonoRepositoryMaria telefonoRepositoryMaria;

    @Autowired
    private TelefonoMapperMaria telefonoMapperMaria;

    public Phone save(Phone telefono) {
        log.info("Into save on Adapter MariaDB");
        TelefonoEntity persistedTelefono = telefonoRepositoryMaria.save(telefonoMapperMaria.fromDomainToAdapter(telefono));
        return telefonoMapperMaria.fromAdapterToDomain(persistedTelefono);
    }

//    @Override
//    public Boolean delete(Integer identification) {
//        return null;
//    }

    public Boolean delete(String id) {
        log.info("Into delete on Adapter MariaDB");
        telefonoRepositoryMaria.deleteById(id);
        return telefonoRepositoryMaria.findById(id).isEmpty();
    }

    public List<Phone> find() {
        log.info("Into find on PhoneOutputAdapterMaria");
        return telefonoRepositoryMaria.findAll().stream().map(telefonoMapperMaria::fromAdapterToDomain).collect(Collectors.toList());
    }

    @Override
    public Phone findByNumber(String number) {
        log.info("Into findById on Adapter MariaDB");
        if (telefonoRepositoryMaria.findById(number).isEmpty
                ()) {
            return null;
        } else {
            return telefonoMapperMaria.fromAdapterToDomain(telefonoRepositoryMaria.findById(number).get());
        }
    }

    @Override
    public Long count() {
        return telefonoRepositoryMaria.count();
    }


}
