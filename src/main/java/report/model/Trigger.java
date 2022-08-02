package report.model;

public enum Trigger {
    HEMOGLOBINE("hemoglobine A1C", "hemoglobin A1C"),
    MICROALBUMINE("microalbumine", "microalbumin"),
    TAILLE("taille", "height"),
    POIDS("poids", "weight"),
    FUMEUR("fumeur", "smoker"),
    ANORMAL("anormal", "abnormal"),
    CHOLESTEROL("cholestérol", "cholesterol"),
    VERTIGE("vertige", "dizziness"),
    RECHUTE("rechute", "relapse"),
    REACTION("réaction", "reaction"),
    ANTICORPS("anticorps", "antibodies");

    private final String french;
    private final String english;

    Trigger(String french, String english) {
        this.french = french;
        this.english = english;
    }

    public String getFrench(){ return french;}
    public String getEnglish(){ return english;}
}
