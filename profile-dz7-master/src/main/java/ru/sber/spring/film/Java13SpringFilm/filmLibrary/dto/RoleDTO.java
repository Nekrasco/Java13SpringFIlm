package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO extends GenericDTO {

    private String title;

    private String description;

    private Set<Long> usersIds;

}
