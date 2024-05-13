package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Profession;

import java.util.List;

public interface ProfessionInputPort {

    public void setPersintence(ProfessionOutputPort phonePersistence);

    public Profession create(Profession phone);

    public Profession edit(Integer identification, Profession phone) throws NoExistException;

    public Boolean drop(Integer identification) throws NoExistException;

    public List<Profession> findAll();

    public Profession findOne(Integer identification) throws NoExistException;

    public Long count();
}
