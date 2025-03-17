package com.epf.api.controller;

import com.epf.api.dto.ReportDto;
import com.epf.api.mapper.ReportDtoMapper;
import com.epf.core.model.Report;
import com.epf.core.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    
    private final ReportService reportService;
    private final ReportDtoMapper dtoMapper;
    
    @Autowired
    public ReportController(ReportService reportService, ReportDtoMapper dtoMapper) {
        this.reportService = reportService;
        this.dtoMapper = dtoMapper;
    }
    
    @GetMapping
    public List<ReportDto> getAllReports() {
        List<Report> reports = reportService.findAll();
        return dtoMapper.mapListModelToListDto(reports);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable Long id) {
        Report report = reportService.findById(id);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dtoMapper.mapModelToDto(report), HttpStatus.OK);
    }
}