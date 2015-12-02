package DAO.Horses;

public class HorsesBean {
    private int idHorse;
    private String name;
    private int age;
    private String bread;

    public HorsesBean(){}

    public HorsesBean(int idHorse, String name, int age, String bread) {
        this.idHorse = idHorse;
        this.name = name;
        this.age = age;
        this.bread = bread;
    }

    public int getIdHorse() {
        return idHorse;
    }

    public void setIdHorse(int idHorse) {
        this.idHorse = idHorse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }
}
