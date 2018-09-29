package com.haiwen.school.zx.beans;

public class Audittype {
    private Integer id;

    private String audittype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAudittype() {
        return audittype;
    }

    public void setAudittype(String audittype) {
        this.audittype = audittype == null ? null : audittype.trim();
    }
}