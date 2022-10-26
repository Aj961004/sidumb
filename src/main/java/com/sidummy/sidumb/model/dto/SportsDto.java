package com.sidummy.sidumb.model.dto;

public class SportsDto {

    private Integer idSports;

    private String sportsName;

    private Integer playersInOneTeam;

    private Integer matchDuration;

    public Integer getIdSports() {
        return idSports;
    }

    public void setIdSports(Integer idSports) {
        this.idSports = idSports;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public Integer getPlayersInOneTeam() {
        return playersInOneTeam;
    }

    public void setPlayersInOneTeam(Integer playersInOneTeam) {
        this.playersInOneTeam = playersInOneTeam;
    }

    public Integer getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(Integer matchDuration) {
        this.matchDuration = matchDuration;
    }
}
