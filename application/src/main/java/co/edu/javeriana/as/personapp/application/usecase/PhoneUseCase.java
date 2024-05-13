package co.edu.javeriana.as.personapp.application.usecase;

import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Slf4j
@UseCase
public class PhoneUseCase implements PhoneInputPort {

    private PhoneOutputPort phonePersistence;

    public PhoneUseCase(@Qualifier("PhoneOutputAdapterMaria") PhoneOutputPort phonePersistence) {
        this.phonePersistence =phonePersistence;
    }

    @Override
    public void setPersintence(PhoneOutputPort phonePersistence) {
        log.info("Into setPersistence in PhoneUseCase using PhoneOutputPort");
        this.phonePersistence = phonePersistence;
    }

    @Override
    public Phone create(Phone phone) {
        log.debug("Into create on Application Domain");
        return phonePersistence.save(phone);
    }

    @Override
    public Phone edit(String number, Phone phone) throws NoExistException {
        log.info("Into edit in PhoneUseCase using PhoneOutputPort");
        Phone oldPhone = phonePersistence.findByNumber(number);
        if (oldPhone != null)
            return phonePersistence.save(phone);
        throw new NoExistException(
                "The phone with number " + number + " does not exist into db, cannot be edited");
    }

//    @Override
//    public Boolean drop(String number) throws NoExistException {
//        log.info("Into drop in PhoneUseCase using PhoneOutputPort");
//        Phone oldPhone = phonePersistence.findByNumber(number);
//        if (oldPhone != null)
//            return phonePersistence.delete(number);
//        throw new NoExistException(
//                "The phone with number " + number + " does not exist into db, cannot be dropped");
//    }

    @Override
    public List<Phone> findAll() {
        log.info("Into findAll in PhoneUseCase using PhoneOutputPort");
        return phonePersistence.find();
    }

    @Override
    public Phone findOne(String number) throws NoExistException {
        log.info("Into findOne in PhoneUserCase using PhoneOutputPort receiving" + phonePersistence.findByNumber(number));
        Phone oldPhone = phonePersistence.findByNumber(number);
        if (oldPhone != null)
            return oldPhone;
        throw new NoExistException("The phone with number " + number + " does not exist into db, cannot be found");
    }

    @Override
    public Long count() {
        log.info("Into count in PhoneUseCase");
        return phonePersistence.count();
    }
}
