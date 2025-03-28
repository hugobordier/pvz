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
import com.epf.core.model.Plante;
import com.epf.infrastructure.entities.PlanteEntity;
import com.epf.infrastructure.interfaces.PlanteDaoInterface;

@Repository
public class PlanteDAO implements PlanteDaoInterface {
    
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PlanteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<PlanteEntity> findAll() {
        String sql = "SELECT id_plante, nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image FROM Plante";
        return jdbcTemplate.query(sql, new PlanteRowMapper());
    }
    
    @Override
    public PlanteEntity findById(Integer id) {
        String sql = "SELECT id_plante, nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image FROM Plante WHERE id_plante = ?";
        List<PlanteEntity> plantes = jdbcTemplate.query(sql, new PlanteRowMapper(), id);
        return plantes.isEmpty() ? null : plantes.get(0);
    }
    
    private static class PlanteRowMapper implements RowMapper<PlanteEntity> {
        @Override
        public PlanteEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlanteEntity plante = new PlanteEntity();
            plante.setIdPlante(rs.getInt("id_plante"));
            plante.setNom(rs.getString("nom"));
            plante.setPointDeVie(rs.getInt("point_de_vie"));
            plante.setAttaqueParSeconde(rs.getDouble("attaque_par_seconde"));
            plante.setDegatAttaque(rs.getInt("degat_attaque"));
            plante.setCout(rs.getInt("cout"));
            plante.setSoleilParSeconde(rs.getDouble("soleil_par_seconde"));
            plante.setEffet(rs.getString("effet"));
            plante.setCheminImage(rs.getString("chemin_image"));
            return plante;
        }
    }

    @Override
    public Plante save(Plante plante) {
        if (plante.getIdPlante() == null) {
            String sql = "INSERT INTO Plante (nom, point_de_vie, attaque_par_seconde, degat_attaque, cout, soleil_par_seconde, effet, chemin_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, plante.getNom(), plante.getPointDeVie(), plante.getAttaqueParSeconde(), plante.getDegatAttaque(), plante.getCout(), plante.getSoleilParSeconde(), plante.getEffet(), plante.getCheminImage());
        } else {
            String sql = "UPDATE Plante SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, cout = ?, soleil_par_seconde = ?, effet = ?, chemin_image = ? WHERE id_plante = ?";
            jdbcTemplate.update(sql, plante.getNom(), plante.getPointDeVie(), plante.getAttaqueParSeconde(), plante.getDegatAttaque(), plante.getCout(), plante.getSoleilParSeconde(), plante.getEffet(), plante.getCheminImage(), plante.getIdPlante());
        }
        
        return plante; 
    }

    @Override
    public void delete(Integer id) {
        String deletePlanteSql = "DELETE FROM Plante WHERE id_plante = ?";
        try {
            jdbcTemplate.update(deletePlanteSql, id);
        } catch (DataAccessException e) {
            System.out.println(e);
            throw new ApiError("Erreur lors de la suppression de la plante.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
