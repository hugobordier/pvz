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
import com.epf.core.model.Zombie;
import com.epf.infrastructure.entities.ZombieEntity;

@Repository
public class ZombieDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ZombieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    public List<ZombieEntity> findAll() {
        String sql = "SELECT id_zombie, nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map FROM Zombie";
        return jdbcTemplate.query(sql, new ZombieRowMapper());
    }

    
    public ZombieEntity findById(Integer id) {
        String sql = "SELECT id_zombie, nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map FROM Zombie WHERE id_zombie = ?";
        List<ZombieEntity> zombies = jdbcTemplate.query(sql, new ZombieRowMapper(), id);
        return zombies.isEmpty() ? null : zombies.get(0);
    }

    private static class ZombieRowMapper implements RowMapper<ZombieEntity> {
        
        @Override
        public ZombieEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ZombieEntity zombie = new ZombieEntity();
            zombie.setIdZombie(rs.getInt("id_zombie"));
            zombie.setNom(rs.getString("nom"));
            zombie.setPointDeVie(rs.getInt("point_de_vie"));
            zombie.setAttaqueParSeconde(rs.getDouble("attaque_par_seconde"));
            zombie.setDegatAttaque(rs.getInt("degat_attaque"));
            zombie.setVitesseDeDeplacement(rs.getDouble("vitesse_de_deplacement"));
            zombie.setCheminImage(rs.getString("chemin_image"));
            zombie.setIdMap(rs.getInt("id_map"));
            return zombie;
        }
    }

    
    public Zombie save(Zombie zombie) {
        try {
             if (zombie.getIdZombie() == null) {
            String sql = "INSERT INTO Zombie (nom, point_de_vie, attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, zombie.getNom(), zombie.getPointDeVie(), zombie.getAttaqueParSeconde(),
                zombie.getDegatAttaque(), zombie.getVitesseDeDeplacement(), zombie.getCheminImage(), zombie.getIdMap());
        } else {
            String sql = "UPDATE Zombie SET nom = ?, point_de_vie = ?, attaque_par_seconde = ?, degat_attaque = ?, vitesse_de_deplacement = ?, chemin_image = ?, id_map = ? WHERE id_zombie = ?";
            jdbcTemplate.update(sql, zombie.getNom(), zombie.getPointDeVie(), zombie.getAttaqueParSeconde(),
                zombie.getDegatAttaque(), zombie.getVitesseDeDeplacement(), zombie.getCheminImage(), zombie.getIdMap(), zombie.getIdZombie());
        }

        return zombie;
        } 
        catch (DataAccessException e) {
            throw new ApiError("Erreur lors de la suppression du zombie.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    public void delete(Integer id) {
        String deleteZombieSql = "DELETE FROM Zombie WHERE id_zombie = ?";
        try {
            jdbcTemplate.update(deleteZombieSql, id);
        } catch (DataAccessException e) {
            System.out.println(e);
            throw new ApiError("Erreur lors de la suppression du zombie.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
