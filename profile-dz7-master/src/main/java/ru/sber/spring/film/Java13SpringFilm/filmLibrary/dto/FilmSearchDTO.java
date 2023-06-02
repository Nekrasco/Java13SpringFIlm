package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Genre;

@Getter
@Setter
@ToString
public class FilmSearchDTO {
    private String filmTitle;
    private String directorsFio;
    private Genre genre;
}

