package com.mdoldur.usertask.controller;

import com.mdoldur.usertask.dto.LoginResultDTO;
import com.mdoldur.usertask.service.interfaces.LoginService;
import com.mdoldur.usertask.service.interfaces.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        return "index"; // resources/templates/index.html
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login"; // resources/templates/login.html
    }

    @RequestMapping(value = "/loginAction", method = RequestMethod.POST)
    public String loginAction(Model model, @ModelAttribute(name = "loginResultDTO") LoginResultDTO loginResultDTO) {
        Boolean result = loginService.isLoggedIn(loginResultDTO);
        if (result) {
            loginResultDTO.setLoggedIn(true);
            return "home";
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

}
