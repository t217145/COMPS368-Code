package comps368.u5.crud.controllers;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import comps368.u5.crud.models.Contacts;
import comps368.u5.crud.models.CustomException;
import comps368.u5.crud.repos.ContactsRepo;
import comps368.u5.crud.utils.CustomValidator;
import jakarta.validation.Valid;

@Controller
public class CrudController {

    private static final Logger log = LoggerFactory.getLogger(CrudController.class);

    @Autowired
    private CustomValidator validator;

    @Autowired
    private ContactsRepo repo;

    @GetMapping({ "", "/", "/index" })
    public String index(ModelMap m) {
        m.put("contacts", repo.findAll());
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

        repo.save(myContacts);
        log.debug("Record created: {}", myContacts);

        return "redirect:/index";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, ModelMap m) throws CustomException {
        Optional<Contacts> oContacts = repo.findById(id);
        if (!oContacts.isPresent()) {
            throw new CustomException("-1", "Record not found!", "/index");
        }
        m.addAttribute("myContacts", oContacts.get());
        return "form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("myContacts") Contacts myContacts, BindingResult result, ModelMap m) {
        validator.validate(myContacts, result);
        if (result.hasErrors()) {
            m.addAttribute("myContacts", myContacts);
            return "form";
        }

        repo.save(myContacts);
        log.debug("Record updated: {}", myContacts);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, ModelMap m) throws CustomException {
        Optional<Contacts> oContacts = repo.findById(id);
        if (!oContacts.isPresent()) {
            throw new CustomException("-1", "Record not found!", "/index");
        }
        repo.delete(oContacts.get());
        log.debug("Record deleted: {}", oContacts.get());

        return "redirect:/index";
    }
}