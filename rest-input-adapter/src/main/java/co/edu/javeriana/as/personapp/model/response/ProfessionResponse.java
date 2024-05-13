package co.edu.javeriana.as.personapp.model.response;

import co.edu.javeriana.as.personapp.model.request.ProfessionRequest;

public class ProfessionResponse extends ProfessionRequest {

    private String status;

    public ProfessionResponse(String id, String name, String description, String database, String status) {
        super(id, name, description, database);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
