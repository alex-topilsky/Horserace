package DAO.Users;

public class UserBean {

    private int idUser;
    private String name;
    private String login;
    private String password;
    private double balance;
    private String role;

    public UserBean(int idUser, String name, String login, String password, double balance, String role) {
        this.idUser = idUser;
        this.name = name;
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
