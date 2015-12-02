package DAO;

public class RaceBean {
    private int idRace;
    private int idRaces;
    private int idHorse;
    private boolean win;

    public RaceBean(int idRace, int idRaces, int idHorse, boolean win) {
        this.idRace = idRace;
        this.idRaces = idRaces;
        this.idHorse = idHorse;
        this.win = win;
    }

    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    public int getIdRaces() {
        return idRaces;
    }

    public void setIdRaces(int idRaces) {
        this.idRaces = idRaces;
    }

    public int getIdHorse() {
        return idHorse;
    }

    public void setIdHorse(int idHorse) {
        this.idHorse = idHorse;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
