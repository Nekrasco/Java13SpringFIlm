package ru.sber.spring.film.Java13SpringFilm.filmLibrary.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.DirectorWithFilmsDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Director;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.GenericModel;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository.FilmRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

    @Component
    public class DirectorWithFilmsMapper extends GenericMapper<Director, DirectorWithFilmsDTO> {

        private final FilmRepository filmRepository;

        protected DirectorWithFilmsMapper(ModelMapper modelMapper, FilmRepository filmRepository) {
            super(modelMapper, Director.class, DirectorWithFilmsDTO.class);
            this.filmRepository = filmRepository;
        }

        @PostConstruct
        @Override
        protected void setupMapper() {
            modelMapper.createTypeMap(Director.class, DirectorWithFilmsDTO.class)
                    .addMappings(m -> m.skip(DirectorWithFilmsDTO::setFilmsIds)).setPostConverter(toDtoConverter());
            modelMapper.createTypeMap(DirectorWithFilmsDTO.class, Director.class)
                    .addMappings(m -> m.skip(Director::setFilms)).setPostConverter(toEntityConverter());
        }

        @Override
        protected void mapSpecificFields(DirectorWithFilmsDTO source, Director destination) {
            destination.setFilms(new HashSet<>(filmRepository.findAllById(source.getFilmsIds())));
        }

        @Override
        protected void mapSpecificFields(Director source, DirectorWithFilmsDTO destination) {
            destination.setFilmsIds(Objects.isNull(source) || Objects.isNull(source.getId()) ? null
                    : source.getFilms()
                    .stream()
                    .map(GenericModel::getId)
                    .collect(Collectors.toSet()));
        }
    }

