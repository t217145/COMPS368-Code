package comps368.u5.basic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
    
    //add a comment here
    @GetMapping({"", "/", "/index"})
    public String index(ModelMap m){
        m.addAttribute("myName", "Cyrus Cheng");
        m.addAttribute("myAge", "39");
        return "index";
    }

}
