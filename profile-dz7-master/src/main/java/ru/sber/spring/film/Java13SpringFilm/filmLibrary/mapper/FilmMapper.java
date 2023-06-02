package ru.sber.spring.film.Java13SpringFilm.filmLibrary.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.FilmDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Film;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.GenericModel;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository.DirectorRepository;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository.OrderRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class FilmMapper extends GenericMapper<Film, FilmDTO> {

    private final ModelMapper modelMapper;
    private final DirectorRepository directorRepository;
    private final OrderRepository orderRepository;

    protected FilmMapper(ModelMapper modelMapper, DirectorRepository directorRepository,
                         OrderRepository orderRepository) {
        super(modelMapper, Film.class, FilmDTO.class);
        this.modelMapper = modelMapper;
        this.directorRepository = directorRepository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Film.class, FilmDTO.class)
                .addMappings(m -> m.skip(FilmDTO::setDirectorsIds)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(FilmDTO::setOrdersIds)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(FilmDTO.class, Film.class)
                .addMappings(m -> m.skip(Film::setDirector)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Film::setOrders)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(FilmDTO source, Film destination) {
        if (!Objects.isNull(source.getDirectorsIds())) {
            destination.setDirector(new HashSet<>(directorRepository.findAllById(source.getDirectorsIds())));
        } else {
            destination.setDirector(Collections.emptySet());
        }
        if (!Objects.isNull(source.getOrdersIds())) {
            destination.setOrders(new HashSet<>(orderRepository.findAllById(source.getOrdersIds())));
        } else {
            destination.setOrders(Collections.emptySet());
        }
    }

    @Override
    protected void mapSpecificFields(Film source, FilmDTO destination) {
        destination.setDirectorsIds(Objects.isNull(source) || Objects.isNull(source.getDirector()) ? null
                : source.getDirector().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet()));
        destination.setOrdersIds(Objects.isNull(source) || Objects.isNull(source.getOrders()) ? null
                : source.getOrders().stream()
                .map(GenericModel::getId)
                .collect(Collectors.toSet()));

    }
}

