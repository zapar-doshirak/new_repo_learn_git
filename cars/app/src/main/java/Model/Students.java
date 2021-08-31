package Model;

public class Students {
    private int id;
    private String department;
    private String name;
    private String surname;
    private int score;

    public Students(int id, String department, String name, String surname, int score) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.surname = surname;
        this.score = score;
    }

    public Students(String department, String name, String surname, int score) {
        this.department = department;
        this.name = name;
        this.surname = surname;
        this.score = score;
    }

    public Students() {
    }

    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
