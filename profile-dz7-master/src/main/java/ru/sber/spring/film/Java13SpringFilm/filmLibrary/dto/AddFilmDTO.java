package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddFilmDTO {
    Long FilmsId;
    Long DirectorsId;
}
