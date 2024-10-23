package com.tecmilenio.conexionsqliteapp.pojo;

public class Information {

    private Long id;
    private String name;
    private String university;
    private String carrer;

    public Information (){}

    public Information(String name, String university, String carrer) {
        this.name = name;
        this.university = university;
        this.carrer = carrer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }
}
