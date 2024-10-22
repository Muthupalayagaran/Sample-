package com.Oranium.Demo.DemoApplication.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/showLoginPage")
    public String showLogin()
    {
        return "Login_page";

    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";
    }

}
