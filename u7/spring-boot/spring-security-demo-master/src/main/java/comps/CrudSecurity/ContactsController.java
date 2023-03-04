package comps.CrudSecurity;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactsController{

    @Autowired
    private ContactsRepository svc;

    @GetMapping({"/","/index"})
    public String index(ModelMap m){
        return "index";
    }

    @GetMapping("/list")
    public String list(ModelMap m){
        m.addAttribute("allContacts", svc.findAll());
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap m){
        m.addAttribute("objs", svc.findById(id).get());
        return "detail";
    }

    @GetMapping("/create")
    public String create(ModelMap m){
        m.addAttribute("objs", new Contacts());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("objs") Contacts c, BindingResult result, ModelMap m){
        if(result.hasErrors()){
            m.addAttribute("objs", c);
            return "create";
        } else {
            svc.save(c);
            return "redirect:/list";
        }
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, ModelMap m){
        m.addAttribute("objs", svc.findById(id).get());
        return "update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("objs") Contacts c, BindingResult result, ModelMap m){
        if(result.hasErrors()){
            m.addAttribute("objs", c);
            return "update";
        } else {
            svc.save(c);
            return "redirect:/list";
        }
    } 
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        svc.deleteById(id);
        return "redirect:/list";
    }    
}