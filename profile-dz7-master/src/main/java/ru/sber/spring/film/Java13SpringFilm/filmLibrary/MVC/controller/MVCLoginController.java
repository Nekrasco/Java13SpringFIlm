package ru.sber.spring.film.Java13SpringFilm.filmLibrary.MVC.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Hidden
public class MVCLoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

