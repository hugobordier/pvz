package com.epf.infrastructure.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epf.core.model.Report;
import com.epf.infrastructure.dao.ReportDAO;
import com.epf.infrastructure.mapper.ReportEntityMapper;

@Repository
public class ReportRepository {
    
    private final ReportDAO reportDAO;
    private final ReportEntityMapper mapper;
    
    @Autowired
    public ReportRepository(ReportDAO reportDAO, ReportEntityMapper mapper) {
        this.reportDAO = reportDAO;
        this.mapper = mapper;
    }
    
    public List<Report> findAll() {
        return mapper.mapListEntityToListModel(reportDAO.findAll());
    }
    
    public Report findById(Long id) {
        return mapper.mapEntityToModel(reportDAO.findById(id));
    }
}