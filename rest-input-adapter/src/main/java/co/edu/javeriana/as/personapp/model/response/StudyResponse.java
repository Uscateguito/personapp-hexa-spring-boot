package co.edu.javeriana.as.personapp.model.response;

import co.edu.javeriana.as.personapp.model.request.StudyRequest;

public class StudyResponse extends StudyRequest {

    private String status;

    public StudyResponse(String professionId, String personId, String date, String university, String database, String status) {
        super(professionId, personId, date, university, database);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
