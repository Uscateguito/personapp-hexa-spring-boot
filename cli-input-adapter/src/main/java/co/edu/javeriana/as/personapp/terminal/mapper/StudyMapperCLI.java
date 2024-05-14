package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.adapter.PersonInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.adapter.ProfessionInputAdapterCLI;
import co.edu.javeriana.as.personapp.terminal.model.StudyModelCLI;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class StudyMapperCLI {

    @Autowired
    private PersonInputAdapterCLI personInputAdapterCLI;

    @Autowired
    private PersonMapperCLI personMapperCLI;

    @Autowired
    private ProfessionInputAdapterCLI professionInputAdapterCLI;

    @Autowired
    private ProfessionMapperCLI professionMapperCLI;


    public StudyModelCLI fromDomainToAdapterMaria(Study study)
    {
        return fromDomainToAdapterCli(study, "MariaDB");
    }

    public StudyModelCLI fromDomainToAdapterMongo(Study study)
    {
        return fromDomainToAdapterCli(study, "MongoDB");
    }

    public StudyModelCLI fromDomainToAdapterCli(Study study, String database)
    {
        return new StudyModelCLI(
                study.getGraduationDate(),
                study.getUniversityName(),
                study.getPerson().getIdentification().toString(),
                study.getProfession().getIdentification().toString(),
                database
        );
    }

    public Study fromAdapterCliToDomain(StudyModelCLI estudioModelCli)
    {
        return Study.builder()
                .graduationDate(estudioModelCli.getGraduationDate())
                .universityName(estudioModelCli.getUniversityName())
                .person(
                        personMapperCLI.fromAdapterCliToDomain(
                                personInputAdapterCLI
                                        .getById(Integer.valueOf(estudioModelCli.getIdPerson()), estudioModelCli.getDb())
                        )
                )
                .profession(
                        professionMapperCLI.fromAdapterCliToDomain(
                                professionInputAdapterCLI
                                        .getById(Integer.valueOf(estudioModelCli.getIdProfession()), estudioModelCli.getDb())
                        )
                )
                .build();
    }
}
