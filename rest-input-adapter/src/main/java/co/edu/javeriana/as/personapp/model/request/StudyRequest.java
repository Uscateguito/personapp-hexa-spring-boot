package co.edu.javeriana.as.personapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyRequest {

    private String professionId;
    private String personId;
    private String Graduationdate;
    private String universityName;
    private String database;
}

