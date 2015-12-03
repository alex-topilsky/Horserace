package DAO.Race;

public class RaceBean {
    private int idRace;
    private int idRaces;
    private int idHorse;
    private String winner;

    public RaceBean(){}

    public RaceBean(int idRace, int idRaces, int idHorse, String win) {
        this.idRace = idRace;
        this.idRaces = idRaces;
        this.idHorse = idHorse;
        this.winner = win;
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

    public String getWinner() {
        return winner;
    }

    public void setWinner(String win) {
        this.winner = win;
    }
}
