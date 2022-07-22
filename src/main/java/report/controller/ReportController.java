package report.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import report.model.Patient;
import report.service.ReportService;

import java.util.List;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    public List<Patient> getAllPatients(){
        return reportService.getAllPatients();
    }
}