package model;

public class Dentist {

    private Integer id;
    private Integer registration;
    private String name;
    private String lastname;

    public Dentist() {
    }


    public Dentist(Integer id, Integer registration, String name, String lastname) {
        this.id = id;
        this.registration = registration;
        this.name = name;
        this.lastname = lastname;
    }

    public Dentist(Integer registration, String name, String lastname) {
        this.registration = registration;
        this.name = name;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
