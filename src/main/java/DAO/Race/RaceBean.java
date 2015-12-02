package DAO.Race;

public class RaceBean {
    private int idRace;
    private int idRaces;
    private int idHorse;
    private boolean winner;

    public RaceBean(){}

    public RaceBean(int idRace, int idRaces, int idHorse, boolean win) {
        this.idRace = idRace;
        this.idRaces = idRaces;
        this.idHorse = idHorse;
        this.winner = winner;
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

    public boolean getWinner() {
        return winner;
    }

    public void setWinner(boolean win) {
        this.winner = winner;
    }
}
