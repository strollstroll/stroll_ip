package com.haiwen.school.zx.beans;

public class Profession {
    private Integer id;

    private String teachertype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeachertype() {
        return teachertype;
    }

    public void setTeachertype(String teachertype) {
        this.teachertype = teachertype == null ? null : teachertype.trim();
    }
}