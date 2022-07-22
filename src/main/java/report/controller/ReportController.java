package report.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import report.model.Note;
import report.model.Patient;
import report.service.ReportService;

import java.util.List;

@RestController
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
}
