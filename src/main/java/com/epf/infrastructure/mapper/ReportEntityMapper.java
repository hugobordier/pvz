package com.epf.infrastructure.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.epf.core.model.Report;
import com.epf.infrastructure.entities.ReportEntity;

@Component
public class ReportEntityMapper {
    
    public Report mapEntityToModel(ReportEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Report report = new Report();
        report.setId(entity.getId());
        report.setFirstName(entity.getFirstName());
        report.setLastName(entity.getLastName());
        report.setSubmissionDate(entity.getSubmissionDate());
        report.setScore(entity.getScore());
        return report;
    }
    
    public List<Report> mapListEntityToListModel(List<ReportEntity> entities) {
        return entities.stream()
                .map(this::mapEntityToModel)
                .toList();
    }
}