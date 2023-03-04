package comps368.u7.jdbcauth.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"","/","/index"})
    public String index(ModelMap m){
        return "index";
    }
}