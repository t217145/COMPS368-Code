/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u7.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class TestController {
    
    @GetMapping({"","Index"})
    public String index(ModelMap m){
        m.addAttribute("str", "Hello, User");
        return "index";
    }
    
}
