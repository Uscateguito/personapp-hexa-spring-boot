package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.model.request.ProfessionRequest;
import co.edu.javeriana.as.personapp.model.response.ProfessionResponse;

@Mapper
public class ProfessionMapperRest {

        public ProfessionResponse fromDomainToAdapterRestMaria(Profession profession) {
            return fromDomainToAdapterRest(profession, "MariaDB");
        }
        public ProfessionResponse fromDomainToAdapterRestMongo(Profession profession) {
            return fromDomainToAdapterRest(profession, "MongoDB");
        }

        public ProfessionResponse fromDomainToAdapterRest(Profession profession, String database) {
            return new ProfessionResponse(
                    profession.getIdentification().toString(),
                    profession.getName(),
                    profession.getDescription(),
                    database,
                    "OK"
            );
        }

        public Profession fromAdapterToDomain(ProfessionRequest response) {
            try {
                return Profession.builder()
                        .identification(Integer.parseInt(response.getId()))
                        .name(response.getName())
                        .description(response.getDescription())
                        .build();
            }
            catch (Exception e) {
                e.getMessage();
            }
            return null;
        }

}
