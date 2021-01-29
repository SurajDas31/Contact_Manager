package com.virus.entity;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull(message = "Name cannot be empty")
    @Size(min = 3, max = 12)
    private String name;
    @Column(unique = true)
    @NotNull(message = "Email connot be empty")
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String mobileNo;
    @NotNull
    private String dateOfBirth;
    @NotNull
    private String city;
    @AssertTrue
    private Boolean agreement;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Contact> contactList;

    private String role;

    public User() {
        super();
    }

    public User(String name, String email, String password, String mobileNo, String dateOfBirth,
                String city, Boolean agreement, List<Contact> contactList, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNo = mobileNo;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.agreement = agreement;
        this.contactList = contactList;
        this.role = role;
    }

    public User(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.mobileNo = user.getPassword();
        this.dateOfBirth = user.getDateOfBirth();
        this.city = user.getCity();
        this.agreement = user.getAgreement();
        this.contactList = user.getContactList();
        this.role = user.getRole();
    }

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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getAgreement() {
        return agreement;
    }

    public void setAgreement(Boolean agreement) {
        this.agreement = agreement;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
