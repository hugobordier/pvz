package com.epf.api.dto;

public class MapDto{
    private Integer id;
    private Integer ligne;
    private Integer colonne;
    private String chemin_image;

    public MapDto() {}

    public MapDto(Integer id, Integer ligne, Integer colonne, String chemin_image) {
        this.id = id;
        this.ligne = ligne;
        this.colonne = colonne;
        this.chemin_image = chemin_image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLigne() {
        return ligne;
    }

    public void setLigne(Integer ligne) {
        this.ligne = ligne;
    }

    public Integer getColonne() {
        return colonne;
    }

    public void setColonne(Integer colonne) {
        this.colonne = colonne;
    }

    public String getCheminImage() {
        return chemin_image;
    }

    public void setchemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }

}
