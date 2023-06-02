package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IdFilmDirectorDTO {
    private Long filmId;
    private Long directorId;
}
