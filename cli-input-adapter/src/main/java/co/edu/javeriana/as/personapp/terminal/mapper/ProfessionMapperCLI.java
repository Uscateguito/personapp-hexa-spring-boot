package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.terminal.model.ProfessionModelCLI;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class ProfessionMapperCLI {

    public ProfessionModelCLI fromDomainToAdapterMaria(Profession profession) {
        return fromDomainToAdapterCli(profession, "MariaDB");
    }

    public ProfessionModelCLI fromDomainToAdapterMongo(Profession profession) {
        return fromDomainToAdapterCli(profession, "MongoDB");
    }

    public ProfessionModelCLI fromDomainToAdapterCli(Profession profession, String database)
    {
        return new ProfessionModelCLI(
                profession.getIdentification(),
                profession.getName(),
                profession.getDescription(),
                database
        );
    }

    public Profession fromAdapterCliToDomain(ProfessionModelCLI profesionModelCli)
    {
        Profession profession = new Profession();
        profession.setIdentification(profesionModelCli.getId());
        profession.setName(profesionModelCli.getName());
        profession.setDescription(profesionModelCli.getDescription());
        return profession;
    }
}
