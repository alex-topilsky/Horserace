package DAO;

public class BetBean {
    private int idBet;
    private int idRace;
    private int idUser;
    private double rate;

    public BetBean(int idBet, int idRace, int idUser, double rate) {
        this.idBet = idBet;
        this.idRace = idRace;
        this.idUser = idUser;
        this.rate = rate;
    }

    public int getIdBet() {
        return idBet;
    }

    public void setIdBet(int idBet) {
        this.idBet = idBet;
    }

    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
