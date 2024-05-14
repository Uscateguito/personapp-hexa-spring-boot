package co.edu.javeriana.as.personapp.terminal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyModelCLI {
    private LocalDate graduationDate;
    private String universityName;
    private String idPerson;
    private String idProfession;
    private String db;

}
