package ru.sber.spring.film.Java13SpringFilm.filmLibrary.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.FilmWithDirectorsDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Film;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.GenericModel;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository.DirectorRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class FilmWithDirectorsMapper extends GenericMapper<Film, FilmWithDirectorsDTO> {

    private final DirectorRepository directorRepository;

    protected FilmWithDirectorsMapper(ModelMapper mapper,
                                      DirectorRepository directorRepository) {
        super(mapper, Film.class, FilmWithDirectorsDTO.class);
        this.directorRepository = directorRepository;
    }

    @PostConstruct
    @Override
    protected void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmWithDirectorsDTO.class)
                .addMappings(m -> m.skip(FilmWithDirectorsDTO::setDirectorsIds)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(FilmWithDirectorsDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirector)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmWithDirectorsDTO source, Film destination) {
        destination.setDirector(new HashSet<>(directorRepository.findAllById(source.getDirectorsIds())));
    }

    @Override
    protected void mapSpecificFields(Film source, FilmWithDirectorsDTO destination) {
        destination.setDirectorsIds(Objects.isNull(source) || Objects.isNull(source.getId()) ? null
                : source.getDirector()
                .stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet()));
    }
    }

