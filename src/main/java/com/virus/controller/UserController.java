package com.virus.controller;

import com.virus.entity.Contact;
import com.virus.entity.User;
import com.virus.repository.ContactRepository;
import com.virus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ModelAttribute
    public void common(Model model, Principal principal) {
        User user = userRepository.getAllByEmail(principal.getName());
        System.out.println("Name: " + user.getName());
        model.addAttribute("user", user);
    }

    @RequestMapping("/")
    public String dashboard(Model model, Principal principal) {
        User user = userRepository.getAllByEmail(principal.getName());
        System.out.println(contactRepository.countByUser_Id(user.getId()));
        model.addAttribute("count", contactRepository.countByUser_Id(user.getId()));
        return "auth/Dashboard";
    }

    @RequestMapping(value = "/AddContact", method = RequestMethod.GET)
    public String addContact(Model model, Principal principal) {
        model.addAttribute("formData", new Contact());
        return "auth/AddContact";
    }

    @RequestMapping(value = "/AddContact", method = RequestMethod.POST)
    public String registerContact(@ModelAttribute("formData") Contact contact, @Param("mobileNo") String mobileNo,
                                  Principal principal, Model model) {
        User user = userRepository.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        System.out.println("Data: " + contact.toString());
       /* if (contactRepository.existsContactByCmobileNoAndUser_Email(mobileNo, user.getId())) {
            model.addAttribute("existingMobileNo");
        }*/
        contact.setUser(user);
        user.getContact().add(contact);
        userRepository.save(user);
        model.addAttribute("success");
        return "auth/AddContact";
    }

    @RequestMapping(value = "/AllContact")
    public String allContacts(Model model, Principal principal) {
        model.addAttribute("contacts", contactRepository.getContactByUser_Email(principal.getName()));
        return "auth/AllContact";
    }

    @RequestMapping(value = "/delete/{cid}", method = RequestMethod.GET)
    public String deleteContact(@PathVariable("cid") int cId, Model model) {
        //    contactRepository.getContactByCid(cId).setUser(null);
        contactRepository.deleteById(cId);
        return "redirect:/auth/AllContact";
    }

    @RequestMapping(value = "/update/{cid}", method = RequestMethod.POST)
    public String updateContact(@PathVariable("cid") int cId, Model model) {
        Contact contact = contactRepository.getContactByCid(cId);
        model.addAttribute("contact", contact);
        return "/auth/UpdateContact";
    }

    @RequestMapping(value = "/SubmitContact", method = RequestMethod.POST)
    public String submitContact(@ModelAttribute("formData") Contact contact, Principal principal) {
        User user = userRepository.getAllByEmail(principal.getName());
        System.out.println("Contact Id : " + contact.getCid());
        contact.setUser(user);

        contactRepository.save(contact);
        return "redirect:/auth/AllContact";
    }

    @RequestMapping("/Settings")
    public String settings(Model model, Principal principal) {
        User user = userRepository.getAllByEmail(principal.getName());
        model.addAttribute("user", user);
        return "/auth/Setting";
    }

    @RequestMapping(value = "/Change_Password", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute User user, @Param("currentPassword") String oldPassword, Principal principal) {
        User newUser = userRepository.getAllByEmail(principal.getName());
        if (newUser.getPassword().equals(oldPassword)) {
            userRepository.save(user);
        }
        return "/auth/Setting";
    }

    @RequestMapping(value = "/UpdateProfile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "/auth/Setting";
    }

}
