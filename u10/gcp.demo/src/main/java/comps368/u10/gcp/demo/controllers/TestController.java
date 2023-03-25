package comps368.u10.gcp.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping({"", "/", "/index"})
    public String index(ModelMap m){
        return "index";
    }
}
