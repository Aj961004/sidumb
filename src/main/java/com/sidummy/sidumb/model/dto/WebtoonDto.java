package com.sidummy.sidumb.model.dto;

public class WebtoonDto {

    private Integer idWebtoon;

    private String webtoonName;

    private String author;

    private String platform;

    private String genre;

    private String status;

    private String originCountry;

    public String getWebtoonName() {
        return webtoonName;
    }

    public void setWebtoonName(String webtoonName) {
        this.webtoonName = webtoonName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public Integer getIdWebtoon() {
        return idWebtoon;
    }

    public void setIdWebtoon(Integer idWebtoon) {
        this.idWebtoon = idWebtoon;
    }
}
