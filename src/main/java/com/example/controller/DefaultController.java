package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @RequestMapping("/indexx")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/adminIndex";
        }
        if (request.isUserInRole("ROLE_ETUDIANT")) {
            return "redirect:/studentIndex";
        }
        if (request.isUserInRole("ROLE_REPRESENTANT")) {
            return "redirect:/representantIndex";
        }
        return "redirect:/login";
    }

}