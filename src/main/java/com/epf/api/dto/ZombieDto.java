package com.epf.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZombieDto {
    private Integer idZombie;
    
    @JsonProperty("nom")
    private String nom;
    
    @JsonProperty("point_de_vie")
    private Integer pointDeVie;
    
    @JsonProperty("attaque_par_seconde")
    private Double attaqueParSeconde;
    
    @JsonProperty("degat_attaque")
    private Integer degatAttaque;
    
    @JsonProperty("vitesse_de_deplacement")
    private Double vitesseDeDeplacement;
    
    @JsonProperty("chemin_image")
    private String cheminImage;
    
    @JsonProperty("id_map")
    private Integer idMap;

    public ZombieDto() {}

    public ZombieDto(Integer idZombie, String nom, Integer pointDeVie, Double attaqueParSeconde, Integer degatAttaque, 
                     Double vitesseDeDeplacement, String cheminImage, Integer idMap) {
        this.idZombie = idZombie;
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.attaqueParSeconde = attaqueParSeconde;
        this.degatAttaque = degatAttaque;
        this.vitesseDeDeplacement = vitesseDeDeplacement;
        this.cheminImage = cheminImage;
        this.idMap = idMap;
    }

    public Integer getIdZombie() {
        return idZombie;
    }

    public void setIdZombie(Integer idZombie) {
        this.idZombie = idZombie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPointDeVie() {
        return pointDeVie;
    }

    public void setPointDeVie(Integer pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public Double getAttaqueParSeconde() {
        return attaqueParSeconde;
    }

    public void setAttaqueParSeconde(Double attaqueParSeconde) {
        this.attaqueParSeconde = attaqueParSeconde;
    }

    public Integer getDegatAttaque() {
        return degatAttaque;
    }

    public void setDegatAttaque(Integer degatAttaque) {
        this.degatAttaque = degatAttaque;
    }

    public Double getVitesseDeDeplacement() {
        return vitesseDeDeplacement;
    }

    public void setVitesseDeDeplacement(Double vitesseDeDeplacement) {
        this.vitesseDeDeplacement = vitesseDeDeplacement;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public Integer getIdMap() {
        return idMap;
    }

    public void setIdMap(Integer idMap) {
        this.idMap = idMap;
    }
}
