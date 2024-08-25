package com.demo.beans;

import java.util.Date;

public class Employee {
    static int empCount=0;

    private int eid;
    private String ename;
    private String phone;
    private String email;
    private String dept;
    private String desg;
    private Date dob;
    private String gender;
    private String username;
    private String password;

    public Employee() {
        this.eid = 0;
        this.ename = null;
        this.phone = null;
        this.email = null;
        this.dept = null;
        this.desg = null;
        this.dob = null;
        this.gender = null;
    }

    public Employee(int eid, String ename, String phone, String email, String dept, String desg, Date dob, String gender) {
        this.eid = generateEmployeeId();
        this.ename = ename;
        this.phone = phone;
        this.email = email;
        this.dept = dept;
        this.desg = desg;
        this.dob = dob;
        this.gender = gender;
    }

    public int generateEmployeeId(){
        empCount++;
        return 100*empCount;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDesg() {
        return desg;
    }

    public void setDesg(String desg) {
        this.desg = desg;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dept='" + dept + '\'' +
                ", desg='" + desg + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                '}';
    }
}
