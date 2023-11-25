package comps368.u5.basic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import comps368.u5.basic.models.FormObj;

@Controller
@RequestMapping("/formPost")
public class FormPostController {

    @Autowired
    private FormObj obj;

    // Sample http://localhost:8080/formPost/index
    @GetMapping({ "", "/", "/index" })
    public String index(ModelMap m) {
        m.addAttribute("formObj", obj);
        return "form";
    }

    @PostMapping("/handleForm")
    public String handleForm(@ModelAttribute("formObj") FormObj obj, ModelMap m) {
        m.addAttribute("myName", obj.getName());
        m.addAttribute("myAge", obj.getAge());
        return "index";
    }
}