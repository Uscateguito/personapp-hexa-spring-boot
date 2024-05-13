package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

public interface StudyInputPort {

    public void setPersintence(StudyOutputPort phonePersistence);

    public Study create(Study study);

    public Study edit(Integer professionId, Integer personId, Study study) throws NoExistException;

//    public Boolean drop(String number) throws NoExistException;

    public List<Study> findAll();

    public Study findOne(Integer professionId, Integer personId) throws NoExistException;

    public Long count();
}
