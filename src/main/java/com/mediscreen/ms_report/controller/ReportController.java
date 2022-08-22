package com.mediscreen.ms_report.controller;

import com.mediscreen.ms_report.model.Note;
import com.mediscreen.ms_report.model.Patient;
import com.mediscreen.ms_report.model.Report;
import com.mediscreen.ms_report.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/patients/list")
    public List<Patient> getAllPatients(){
        System.out.println("Cocuocu");
        return reportService.getAllPatients();
    }

    @GetMapping("/notes/list")
    public List<Note> getAllNotes(){
        System.out.println("Cocuocu");
        return reportService.getAllNotes();
    }

    @GetMapping("/patients/{id}/notes")
    public List<Note> getPatientNotes(@PathVariable String id){
        return reportService.getPatientNotes(id);
    }

    @GetMapping("/report/{id}")
    public Report generateReport(@PathVariable String id){
        return reportService.generateReport(id);
    }
}
