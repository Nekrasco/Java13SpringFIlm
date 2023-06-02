package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmWithDirectorsDTO extends FilmDTO{
    private Set<DirectorDTO> directors;

}
