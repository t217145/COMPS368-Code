package comps368.u5.form.controllers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import comps368.u5.form.models.Contacts;

@Controller
public class FormController {

    private static final Logger log = LoggerFactory.getLogger(FormController.class);

    private List<Contacts> contacts = new ArrayList<>();

    @GetMapping({ "", "/", "/index" })
    public String index(ModelMap m) {
        m.addAttribute("contacts", contacts);
        return "index";
    }

    @GetMapping("/create")
    public String create(ModelMap m) {
        m.addAttribute("myContacts", new Contacts());
        return "form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("myContacts") Contacts c) {
        contacts.add(c);
        log.debug("Record created: {}", c);
        return "redirect:/index";
    }
}