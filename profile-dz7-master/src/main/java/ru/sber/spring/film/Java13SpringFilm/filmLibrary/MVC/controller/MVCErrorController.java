package ru.sber.spring.film.Java13SpringFilm.filmLibrary.MVC.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE;
import static org.springframework.boot.web.servlet.support.ErrorPageFilter.ERROR_REQUEST_URI;

@Controller
@Hidden
@Slf4j
public class MVCErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest httpServletRequest, Model model) {
        log.error("ОЙ! Ошибка {}", httpServletRequest.getAttribute(ERROR_STATUS_CODE));
        model.addAttribute("exception",
                "Ошибка " + httpServletRequest.getAttribute(ERROR_STATUS_CODE) + " в маппинге " +
                        httpServletRequest.getAttribute(ERROR_REQUEST_URI));
        return "error";
    }
}
