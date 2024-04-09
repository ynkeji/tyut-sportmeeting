package com.tyut.sport.pojo;

public class sporter {
    private Integer id;
    private String name;
    private String gender;
    private String college;
    private String phone;
    private String email;


    private String password;

    // getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean check(){
        if (this.name == null) return false;
        if (this.college == null) return false;
        if (this.email == null) return false;
        if (this.id == null) return false;
        if (this.phone == null) return false;
        if (this.gender == null) return false;
        if (this.password == null) return false;
        return true;
    }


}
