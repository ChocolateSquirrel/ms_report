package report.model;

import lombok.Data;

@Data
public class Note {

    private String id;
    private String patientId;
    private String recommendations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patId) {
        this.patientId = patId;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public Note() {}

    public Note(String patId, String recommendations) {
        this.patientId = patId;
        this.recommendations = recommendations;
    }
}
