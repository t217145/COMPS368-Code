package comps368.u7.oauth.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"","/","/index"})
    @PreAuthorize("hasAnyAuthority('APPROLE_VISITOR', 'APPROLE_ADMIN')")
    public String AllUsers(ModelMap m) {
        m.addAttribute("role", "All Users");
        return "index";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('APPROLE_ADMIN')")
    public String AdminOnly(ModelMap m) {
        m.addAttribute("role", "Admin");
        return "index";
    }
}