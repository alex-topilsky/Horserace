package DAO.Races;

import java.sql.Date;

public class RacesBean {
    private int idRaces;
    private double winRate;
    private Date dateRace;

    public RacesBean(int idRaces, double winRate, Date dateRace) {
        this.idRaces = idRaces;
        this.winRate = winRate;
        this.dateRace = dateRace;
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

    public Date getDateRace() {
        return dateRace;
    }

    public void setDateRace(Date dateRace) {
        this.dateRace = dateRace;
    }
}
