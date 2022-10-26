package com.sidummy.sidumb.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "tb_cat")
public class CatPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="idnyameong")
    private Integer idCat;

    private String catName;

    private String breeds;

    private String url;

    public Integer getIdCat() {
        return idCat;
    }

    public void setIdCat(Integer idCat) {
        this.idCat = idCat;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getBreeds() {
        return breeds;
    }

    public void setBreeds(String breeds) {
        this.breeds = breeds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
