package co.edu.javeriana.as.personapp.application.port.out;

import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

@Port
public interface StudiesOutputPort {

        public Study save(Study study);
        public Boolean delete(Integer identification);
        public List<Study> find();
        public Study findById(Integer identification);
        public Long count();

}
