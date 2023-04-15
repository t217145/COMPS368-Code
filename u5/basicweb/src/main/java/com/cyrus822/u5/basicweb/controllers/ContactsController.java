package com.cyrus822.u5.basicweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.cyrus822.u5.basicweb.models.Contacts;
import com.cyrus822.u5.basicweb.repos.ContactsRepo;

@Controller
public class ContactsController {

    @Autowired
    private ContactsRepo repo;

    @GetMapping({"","/","/index"})
    public String index(ModelMap m){
        m.addAttribute("allContacts", repo.findAll());
        return "index";
    } 
    
    @GetMapping("/retrieve/{prefix}")
    public String retrieve(ModelMap m, @PathVariable("prefix") String prefix) throws Exception{
        if(prefix.equals("")){
            throw new Exception("you have input empty prefix");
        }
        m.addAttribute("allContacts", repo.getByNameStartWith(prefix));
        return "index";
    } 

    @GetMapping("/create")
    public String addContacts(ModelMap m){
        m.addAttribute("newContacts", new Contacts());
        return "create";
    }

    @PostMapping("/create")
    public String addContacts(@ModelAttribute("newContacts") Contacts xxxContent, ModelMap m){
     
        repo.save(xxxContent);
        return "redirect:/index";
    }
}
