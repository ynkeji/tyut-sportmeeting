package com.tyut.sport.pojo;

public class login {

    private String username;
    private String password;

    public String getCapture() {
        return capture;
    }

    public void setCapture(String capture) {
        this.capture = capture;
    }

    private String capture;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String  type;

    @Override
    public String toString() {
        return "login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", capture='" + capture + '\'' +
                ", type=" + type +
                '}';
    }
}
