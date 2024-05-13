package co.edu.javeriana.as.personapp.application.port.out;

import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.domain.Profession;

import java.util.List;

@Port
public interface ProfesionOutputPort {

    public Profession save(Profession profesion);
    public Boolean delete(Integer identification);
    public List<Profession> find();
    public Profession findById(Integer identification);
}
