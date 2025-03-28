package com.epf.infrastructure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.epf.api.exception.ApiError;
import com.epf.core.model.Map;
import com.epf.infrastructure.entities.MapEntity;
import com.epf.infrastructure.interfaces.MapDaoInterface;

@Repository
public class MapDAO implements MapDaoInterface {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public MapDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<MapEntity> findAll() {
        String sql = "SELECT id_map, ligne, colonne, chemin_image FROM Map";
        return jdbcTemplate.query(sql, new MapRowMapper());
    }
    
    @Override
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

    @Override
    public Map save(Map map) {
        if (map.getIdMap() == null) {
            String sql = "INSERT INTO Map (ligne, colonne, chemin_image) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getCheminImage());
        } else {
            String sql = "UPDATE Map SET ligne = ?, colonne = ?, chemin_image = ? WHERE id_map = ?";
            jdbcTemplate.update(sql, map.getLigne(), map.getColonne(), map.getCheminImage(), map.getIdMap());
        }
        
        return map; 
    }

    @Override
    public void delete(Integer id) {
        String checkZombieSql = "SELECT COUNT(*) FROM Zombie WHERE id_map = ?";
        Integer count = jdbcTemplate.queryForObject(checkZombieSql, Integer.class, id);
        
        if (count > 0) {
            String updateZombieSql = "UPDATE Zombie SET id_map = NULL WHERE id_map = ?";
            jdbcTemplate.update(updateZombieSql, id);
        }

        String deleteMapSql = "DELETE FROM Map WHERE id_map = ?";
        try {
            jdbcTemplate.update(deleteMapSql, id);
        } catch (DataAccessException e) {
            System.out.println(e);
            throw new ApiError("Erreur lors de la suppression de la map.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}