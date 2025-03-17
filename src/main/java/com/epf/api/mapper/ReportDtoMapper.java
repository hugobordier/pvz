package com.epf.api.mapper;

import com.epf.api.dto.ReportDto;
import com.epf.core.model.Report;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportDtoMapper {
    
    public ReportDto mapModelToDto(Report report) {
        if (report == null) {
            return null;
        }
        
        ReportDto dto = new ReportDto();
        dto.setId(report.getId());
        dto.setFirstName(report.getFirstName());
        dto.setLastName(report.getLastName());
        dto.setSubmissionDate(report.getSubmissionDate());
        dto.setScore(report.getScore());
        return dto;
    }
    
    public List<ReportDto> mapListModelToListDto(List<Report> reports) {
        return reports.stream()
                .map(this::mapModelToDto)
                .collect(Collectors.toList());
    }
}