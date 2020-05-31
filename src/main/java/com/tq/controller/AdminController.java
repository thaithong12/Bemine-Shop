
package com.tq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/home")
    public String viewHome(Model model) {
        model.addAttribute("message", "Admin Home Page");
        return "admin/home";
    }
}
