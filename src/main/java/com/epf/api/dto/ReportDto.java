package com.epf.api.dto;

import java.sql.Date;

public class ReportDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date submissionDate;
    private Integer score;
    
    public ReportDto() {
    }
    
    public ReportDto(Long id, String firstName, String lastName, Date submissionDate, Integer score) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.submissionDate = submissionDate;
        this.score = score;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Date getSubmissionDate() {
        return submissionDate;
    }
    
    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }
    
    public Integer getScore() {
        return score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
}