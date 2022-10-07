package com.mediscreen.ms_report.model;

public enum Trigger {
    HEMOGLOBINE("hémoglobine a1c", "hemoglobin a1c"),
    MICROALBUMINE("microalbumine", "microalbumin"),
    TAILLE("taille", "height"),
    POIDS("poids", "weight"),
    FUMEUR("fume", "smoker"),
    ANORMAL("anormal", "abnormal"),
    ANORMALS("anormaux", "abnormals"),
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
