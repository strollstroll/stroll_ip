package com.haiwen.school.zx.beans;

public class Course {
    private Integer id;

    private String coursename;

    private String courseremake;

    private Integer teacherid;

    private Integer maxsum;

    private Integer realsum;

    private Integer statusid;

    private String startdate;

    private String enddate;

    private Integer coursetype;

    private String typename;

    private String statusname;

    private String teachername;

    private Double credit;

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public String getCourseremake() {
        return courseremake;
    }

    public void setCourseremake(String courseremake) {
        this.courseremake = courseremake == null ? null : courseremake.trim();
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public Integer getMaxsum() {
        return maxsum;
    }

    public void setMaxsum(Integer maxsum) {
        this.maxsum = maxsum;
    }

    public Integer getRealsum() {
        return realsum;
    }

    public void setRealsum(Integer realsum) {
        this.realsum = realsum;
    }

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate == null ? null : startdate.trim();
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
    }

    public Integer getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(Integer coursetype) {
        this.coursetype = coursetype;
    }
}