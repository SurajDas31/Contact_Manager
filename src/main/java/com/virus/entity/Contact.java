package com.virus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;
    private String cname;
    private String cmobileNo;
    @JsonIgnore
    @ManyToOne
    private User user;

    public Contact() {
    }

    public Contact(String cname, String cmobileNo, User user) {
        this.cname = cname;
        this.cmobileNo = cmobileNo;
        this.user = user;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCmobileNo() {
        return cmobileNo;
    }

    public void setCmobileNo(String cmobileNo) {
        this.cmobileNo = cmobileNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
