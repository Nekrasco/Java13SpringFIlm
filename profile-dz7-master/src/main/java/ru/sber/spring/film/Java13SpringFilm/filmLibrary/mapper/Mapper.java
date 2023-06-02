package ru.sber.spring.film.Java13SpringFilm.filmLibrary.mapper;


import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.GenericDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {
    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> dtoList);

    List<D> toDTOs(List<E> entitiesList);
}
