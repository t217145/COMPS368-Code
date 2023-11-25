package comps368.u5.advance.controllers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import comps368.u5.advance.models.Contacts;
import comps368.u5.advance.utils.CustomValidator;
import jakarta.validation.Valid;

@Controller
public class ValidationController {

    private static final Logger log = LoggerFactory.getLogger(ValidationController.class);

    private List<Contacts> contacts = new ArrayList<>();

    @Autowired
    private CustomValidator validator;

    @GetMapping({ "", "/", "/index" })
    public String index(ModelMap m) {
        m.put("contacts", contacts);
        return "index";
    }

    @GetMapping("/create")
    public String create(ModelMap m) {
        m.put("myContacts", new Contacts());
        return "form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("myContacts") Contacts myContacts, BindingResult result, ModelMap m) {
        validator.validate(myContacts, result);
        if (result.hasErrors()) {
            m.addAttribute("myContacts", myContacts);
            return "form";
        }
        contacts.add(myContacts);
        log.debug("Record created: {}", myContacts);
        return "redirect:/index";
    }
}
