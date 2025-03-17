package com.epf.infrastructure.dao;

import com.epf.infrastructure.entities.ReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReportDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ReportDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<ReportEntity> findAll() {
        String sql = "SELECT id, first_name, last_name, submission_date, score FROM reports";
        return jdbcTemplate.query(sql, new ReportRowMapper());
    }
    
    public ReportEntity findById(Long id) {
        String sql = "SELECT id, first_name, last_name, submission_date, score FROM reports WHERE id = ?";
        List<ReportEntity> reports = jdbcTemplate.query(sql, new ReportRowMapper(), id);
        return reports.isEmpty() ? null : reports.get(0);
    }
    
    private static class ReportRowMapper implements RowMapper<ReportEntity> {
        @Override
        public ReportEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ReportEntity report = new ReportEntity();
            report.setId(rs.getLong("id"));
            report.setFirstName(rs.getString("first_name"));
            report.setLastName(rs.getString("last_name"));
            report.setSubmissionDate(rs.getDate("submission_date"));
            report.setScore(rs.getInt("score"));
            return report;
        }
    }
}