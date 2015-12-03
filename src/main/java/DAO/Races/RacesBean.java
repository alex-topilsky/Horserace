package DAO.Races;

import java.sql.Date;

public class RacesBean {
    private int idRaces;
    private double winRate;
    private String dateRace;
    private String nameRaces;
    private String done;

    public RacesBean(){}

    public RacesBean(int idRaces, double winRate, String dateRace, String nameRaces, String done) {
        this.idRaces = idRaces;
        this.winRate = winRate;
        this.dateRace = dateRace;
        this.nameRaces = nameRaces;
        this.done=done;
    }

    public int getIdRaces() {
        return idRaces;
    }

    public void setIdRaces(int idRaces) {
        this.idRaces = idRaces;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public String getDateRace() {
        return dateRace;
    }

    public void setDateRace(String dateRace) {
        this.dateRace = dateRace;
    }

    public String getNameRaces() {
        return nameRaces;
    }

    public void setNameRaces(String nameRaces) {
        this.nameRaces = nameRaces;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
