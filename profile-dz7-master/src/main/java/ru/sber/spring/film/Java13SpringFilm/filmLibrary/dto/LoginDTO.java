package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {
    private String login;
    private String password;
}
