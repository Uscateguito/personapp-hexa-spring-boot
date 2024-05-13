package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.adapter.PersonInputAdapterRest;
import co.edu.javeriana.as.personapp.adapter.ProfessionInputAdapterRest;
import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.StudyRequest;
import co.edu.javeriana.as.personapp.model.response.StudyResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Mapper
public class StudyMapperRest {

    @Autowired
    private PersonInputAdapterRest personInputAdapterRest;

    @Autowired
    private PersonMapperRest personMapperRest;

    @Autowired
    private ProfessionMapperRest professionMapperRest;

    @Autowired
    private ProfessionInputAdapterRest professionInputAdapterRest;

    public StudyResponse fromDomainToAdapterRestMaria(Study study) {
        return fromDomainToAdapterRest(study, "MariaDB");
    }
    public StudyResponse fromDomainToAdapterRestMongo(Study study) {
        return fromDomainToAdapterRest(study, "MongoDB");
    }

    public StudyResponse fromDomainToAdapterRest(Study study, String database) {
        return new StudyResponse(
                study.getProfession().getIdentification().toString()+"",
                study.getPerson().getIdentification().toString()+"",
                study.getGraduationDate().toString()+"",
                study.getUniversityName()+"",
                database,
                "OK"
        );
    }

    public Study fromAdapterToDomain(StudyRequest response) {
        try {
            return Study.builder()
                    .person(
                            personMapperRest.fromAdapterToDomain(personInputAdapterRest
                                    .getById(Integer.valueOf(response.getPersonId()), response.getDatabase()))
                    )
                    .profession(
                            professionMapperRest.fromAdapterToDomain(professionInputAdapterRest
                                    .getById(Integer.valueOf(response.getProfessionId()), response.getDatabase()))
                    )
                    .graduationDate(LocalDate.parse(response.getGraduationdate()))
                    .universityName(response.getUniversityName())
                    .build();
        }
        catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
