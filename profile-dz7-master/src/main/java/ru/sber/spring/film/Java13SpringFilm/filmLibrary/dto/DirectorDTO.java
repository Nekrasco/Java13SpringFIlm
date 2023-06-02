package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DirectorDTO extends GenericDTO{

    private String directorFio;

    private String position;

    private Set<Long> filmsIds;

    private boolean isDeleted;

    public <E> DirectorDTO(String mvc_testAuthorFio, String s, String test_description, HashSet<E> es, boolean b) {
    }
}
