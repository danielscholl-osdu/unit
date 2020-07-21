package org.opengroup.osdu.unitservice.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/swagger"})
    public String swagger() {
        return "redirect:swagger-ui.html";
    }
}