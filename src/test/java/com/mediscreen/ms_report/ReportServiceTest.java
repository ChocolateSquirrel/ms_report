package com.mediscreen.ms_report;


import com.mediscreen.ms_report.model.*;
import com.mediscreen.ms_report.proxy.NoteProxy;
import com.mediscreen.ms_report.proxy.PatientProxy;
import com.mediscreen.ms_report.service.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @MockBean
    private PatientProxy patientProxy;

    @MockBean
    private NoteProxy noteProxy;

    @Test
    public void getLevelTest(){
        assertEquals(LevelRisk.NONE, reportService.getLevel(50, 0, Sex.MALE));
        assertEquals(LevelRisk.BORDERLINE, reportService.getLevel(50, 2, Sex.MALE));
        assertEquals(LevelRisk.IN_DANGER, reportService.getLevel(20, 3, Sex.MALE));
        assertEquals(LevelRisk.IN_DANGER, reportService.getLevel(20, 4, Sex.FEMALE));
        assertEquals(LevelRisk.IN_DANGER, reportService.getLevel(50, 6, Sex.FEMALE));
        assertEquals(LevelRisk.EARLY_ONSET, reportService.getLevel(20, 5, Sex.MALE));
        assertEquals(LevelRisk.EARLY_ONSET, reportService.getLevel(20, 7, Sex.FEMALE));
        assertEquals(LevelRisk.EARLY_ONSET, reportService.getLevel(50, 8, Sex.FEMALE));
    }

    @Test
    public void generateReportTestNone() {
        List<Note> notes = new ArrayList<>();
        Patient patientTestNone = new Patient("Test","TestNone","1966-12-31",Sex.FEMALE, "1 Brookside St", "100-222-3333");
        Note noteTestNone1 = new Note("1", "Patient states that they are 'feeling terrific' Weight at or below recommended level");
        notes.add(noteTestNone1);

        when(patientProxy.getPatient(1)).thenReturn(patientTestNone);
        when(noteProxy.getPatientNotes("1")).thenReturn(notes);

        Report report = reportService.generateReport("1");
        assertEquals(report.getLevel(), LevelRisk.NONE);
    }


    @Test
    public void generateReportTestBorderline() {
        List<Note> notes = new ArrayList<>();
        Patient patientTestBorderline = new Patient("Test","TestBorderline","1945-06-24",Sex.MALE, "2 High St", "200-333-4444");
        Note noteTestBorderLine1 = new Note("2", "Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late");
        Note noteTestBorderLine2 = new Note("2", "Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic");
        notes.add(noteTestBorderLine1);
        notes.add(noteTestBorderLine2);

        when(patientProxy.getPatient(2)).thenReturn(patientTestBorderline);
        when(noteProxy.getPatientNotes("2")).thenReturn(notes);

        Report report = reportService.generateReport("2");
        assertEquals(report.getLevel(), LevelRisk.BORDERLINE);
    }

    @Test
    public void generateReportTestInDanger() {
        List<Note> notes = new ArrayList<>();
        Patient patientTestInDanger = new Patient("Test","TestInDanger","2004-06-18",Sex.MALE, "3 Club Road", "300-444-5555");
        Note noteTestInDanger1 = new Note("3", "Patient states that they are short term Smoker");
        Note noteTestInDanger2 = new Note("3", "Patient states that they quit within last year Patient also complains that of Abnormal breathing spells Lab reports Cholesterol LDL hig");
        notes.add(noteTestInDanger1);
        notes.add(noteTestInDanger2);

        when(patientProxy.getPatient(3)).thenReturn(patientTestInDanger);
        when(noteProxy.getPatientNotes("3")).thenReturn(notes);

        Report report = reportService.generateReport("3");
        assertEquals(report.getLevel(), LevelRisk.IN_DANGER);
    }

    @Test
    public void generateReportTestEarlyOnset() {
        List<Note> notes = new ArrayList<>();
        Patient patientTestEarlyOnset = new Patient("Test","TestEarlyOnset","2002-06-28",Sex.FEMALE, "4 Valley Dr", "400-555-6666");
        Note noteTestEarlyOnset1 = new Note("4", "Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication");
        Note noteTestEarlyOnset2 = new Note("4", "Patient states that they are experiencing back pain when seated for a long time");
        Note noteTestEarlyOnset3 = new Note("4", "Patient states that they are a short term Smoker Hemoglobin A1C above recommended level");
        Note noteTestEarlyOnset4 = new Note("4", "Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction");
        notes.add(noteTestEarlyOnset1);
        notes.add(noteTestEarlyOnset2);
        notes.add(noteTestEarlyOnset3);
        notes.add(noteTestEarlyOnset4);

        when(patientProxy.getPatient(4)).thenReturn(patientTestEarlyOnset);
        when(noteProxy.getPatientNotes("4")).thenReturn(notes);

        Report report = reportService.generateReport("4");
        assertEquals(report.getLevel(), LevelRisk.EARLY_ONSET);
    }






}
