package com.tyut.sport.pojo;

import javax.print.DocFlavor;

public class grade {

    private Integer stu_id;

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Integer getPro_id() {
        return pro_id;
    }

    public void setPro_id(Integer pro_id) {
        this.pro_id = pro_id;
    }

    public String getStu_grade() {
        return stu_grade;
    }

    public void setStu_grade(String stu_grade) {
        this.stu_grade = stu_grade;
    }

    public Integer getPro_grade() {
        return pro_grade;
    }

    public void setPro_grade(Integer pro_grade) {
        this.pro_grade = pro_grade;
    }

    private Integer pro_id;

    private String stu_grade;

    private Integer pro_grade;

}
