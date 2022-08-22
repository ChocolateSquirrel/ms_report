package com.mediscreen.ms_report.model;

public enum Sex {
    MALE("M"),
    FEMALE("F");

    private String abbreviation;

    Sex(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation(){
        return abbreviation;
    }
}
