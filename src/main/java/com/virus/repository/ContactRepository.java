package com.virus.repository;

import com.virus.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> getContactByUser_Email(String email);
    boolean existsContactByCmobileNoAndUser_Email(String mobileNo, int id);
    Contact getContactByCid(int cId);
    int countByUser_Id(int cId);
    //Contact deleteAllByCid(int cId);
}
