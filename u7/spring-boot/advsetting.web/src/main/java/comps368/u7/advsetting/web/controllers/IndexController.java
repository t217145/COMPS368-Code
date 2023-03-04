package comps368.u7.advsetting.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import comps368.u7.advsetting.web.services.DummyServices;

@Controller
public class IndexController {

    @Autowired
    private DummyServices svc;

    @GetMapping({"","/","/index"})
    @PreAuthorize("authenticated")
    public String index(ModelMap m){
        m.addAttribute("role", svc.helloWorld("General"));
        return "index";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminOnly(ModelMap m){
        m.addAttribute("role", svc.helloWorld("Admin"));        
        return "index";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String userOnly(ModelMap m){
        m.addAttribute("role", svc.helloWorld("User"));        
        return "index";
    }    
}