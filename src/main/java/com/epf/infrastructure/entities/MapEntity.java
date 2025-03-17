package com.epf.infrastructure.entities;

public class MapEntity {
    private Integer idMap;
    private Integer ligne;
    private Integer colonne;
    private String cheminImage;
    
    public MapEntity() {
    }
    
    public MapEntity(Integer idMap, Integer ligne, Integer colonne, String cheminImage) {
        this.idMap = idMap;
        this.ligne = ligne;
        this.colonne = colonne;
        this.cheminImage = cheminImage;
    }
    
    public Integer getIdMap() {
        return idMap;
    }
    
    public void setIdMap(Integer idMap) {
        this.idMap = idMap;
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
        return cheminImage;
    }
    
    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
}