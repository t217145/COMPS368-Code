/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u7.web;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Controller
@RequestMapping(value="/adm")
public class admController {
    
    @GetMapping({"","Index"})
    public String index(ModelMap m){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authStr = "";
        for(GrantedAuthority a : user.getAuthorities()){
            authStr += a.getAuthority() + ",";
        }
        m.addAttribute("roles", authStr);
        m.addAttribute("name", user.getUsername());
        return "usr";
    }
}
