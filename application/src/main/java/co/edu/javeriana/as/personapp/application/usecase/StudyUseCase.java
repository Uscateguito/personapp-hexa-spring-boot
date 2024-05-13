package co.edu.javeriana.as.personapp.application.usecase;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Study;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Slf4j
@UseCase
public class StudyUseCase implements StudyInputPort {

    private StudyOutputPort studyPersistence;


    public StudyUseCase(@Qualifier("StudyOutputAdapterMaria")StudyOutputPort studyPersistence) {
        this.studyPersistence = studyPersistence;
    }

    @Override
    public void setPersintence(StudyOutputPort phonePersistence) {
        log.info("Into setPersistence in StudyUseCase using StudyOutputPort");
        this.studyPersistence = phonePersistence;
    }

    @Override
    public Study create(Study study) {
        log.debug("Into create on Application Domain");
        return studyPersistence.save(study);
    }

    @Override
    public Study edit(Integer professionId, Integer personId, Study study) throws NoExistException {
        log.info("Into edit in StudyUseCase using StudyOutputPort");
        Study studyInstance = studyPersistence.findById(professionId, personId);
        if (studyInstance != null)
            return studyPersistence.save(study);
        throw new NoExistException(
                "The study with number " + studyInstance + " does not exist into db, cannot be edited");
    }

    @Override
    public List<Study> findAll() {
        log.info("Into findAll in StudyUseCase using StudyOutputPort");
        return studyPersistence.find();
    }

    @Override
    public Study findOne(Integer professionId, Integer personId) throws NoExistException {
        log.info("Into findOne in StudyUseCase using StudyOutputPort");
        Study studyInstance = studyPersistence.findById(professionId, personId);
        if (studyInstance != null)
            return studyInstance;
        throw new NoExistException(
                "The study with number " + studyInstance + " does not exist into db, cannot be found");
    }

    @Override
    public Long count() {
        return studyPersistence.count();
    }
}
