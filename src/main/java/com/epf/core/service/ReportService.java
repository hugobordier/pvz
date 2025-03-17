package com.epf.core.service;

import com.epf.core.model.Report;
import com.epf.infrastructure.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    
    private final ReportRepository reportRepository;
    
    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    
    public List<Report> findAll() {
        return reportRepository.findAll();
    }
    
    public Report findById(Long id) {
        return reportRepository.findById(id);
    }
}