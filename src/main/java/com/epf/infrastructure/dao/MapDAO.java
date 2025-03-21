package com.epf.infrastructure.dao;

import com.epf.infrastructure.entities.MapEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MapDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public MapDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<MapEntity> findAll() {
        String sql = "SELECT id_map, ligne, colonne, chemin_image FROM Map";
        return jdbcTemplate.query(sql, new MapRowMapper());
    }
    
    public MapEntity findById(Integer id) {
        String sql = "SELECT id_map, ligne, colonne, chemin_image FROM Map WHERE id_map = ?";
        List<MapEntity> maps = jdbcTemplate.query(sql, new MapRowMapper(), id);
        return maps.isEmpty() ? null : maps.get(0);
    }
    
    private static class MapRowMapper implements RowMapper<MapEntity> {
        @Override
        public MapEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            MapEntity map = new MapEntity();
            map.setIdMap(rs.getInt("id_map"));
            map.setLigne(rs.getInt("ligne"));
            map.setColonne(rs.getInt("colonne"));
            map.setCheminImage(rs.getString("chemin_image"));
            return map;
        }
    }
}