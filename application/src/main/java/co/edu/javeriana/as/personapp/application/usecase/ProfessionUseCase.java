package co.edu.javeriana.as.personapp.application.usecase;

import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Profession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Slf4j
@UseCase
public class ProfessionUseCase implements ProfessionInputPort {

    private ProfessionOutputPort professionPersistence;

    public ProfessionUseCase(@Qualifier("ProfessionOutputAdapterMaria") ProfessionOutputPort professionPersistence) {
        this.professionPersistence = professionPersistence;
    }

    @Override
    public void setPersintence(ProfessionOutputPort phonePersistence) {
        log.info("Into setPersistence in ProfessionUseCase using ProfessionOutputPort");
        this.professionPersistence = phonePersistence;
    }

    @Override
    public Profession create(Profession phone) {
        log.debug("Into create on Application Domain");
        return professionPersistence.save(phone);
    }

    @Override
    public Profession edit(Integer identification, Profession phone) throws NoExistException {
        log.info("Into edit in ProfessionUseCase using ProfessionOutputPort");
        Profession oldProfession = professionPersistence.findById(identification);
        if (oldProfession != null)
            return professionPersistence.save(phone);
        throw new NoExistException(
                "The profession with id " + identification + " does not exist into db, cannot be edited");
    }

    @Override
    public Boolean drop(Integer identification) throws NoExistException {
        log.info("Into drop in ProfessionUseCase using ProfessionOutputPort");
        Profession oldProfession = professionPersistence.findById(identification);
        if (oldProfession != null)
            return professionPersistence.delete(identification);
        throw new NoExistException(
                "The profession with id " + identification + " does not exist into db, cannot be dropped");
    }

    @Override
    public List<Profession> findAll() {
        log.info("Into findAll in ProfessionUseCase using ProfessionOutputPort");
        return professionPersistence.find();
    }

    @Override
    public Profession findOne(Integer identification) throws NoExistException {
        log.info("Into findOne in ProfessionUseCase using ProfessionOutputPort");
        Profession oldProfession = professionPersistence.findById(identification);
        if (oldProfession != null)
            return oldProfession;
        throw new NoExistException(
                "The profession with id " + identification + " does not exist into db, cannot be found");
    }

    @Override
    public Long count() {
        log.info("Into count in ProfessionUseCase");
        return professionPersistence.count();
    }
}
