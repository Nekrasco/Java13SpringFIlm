package ru.sber.spring.film.Java13SpringFilm.filmLibrary.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.OrderDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Order;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository.FilmRepository;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository.UserRepository;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.FilmService;


@Component
public class OrderMapper extends GenericMapper<Order, OrderDTO> {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final FilmService filmService;

    protected OrderMapper(ModelMapper modelMapper, FilmRepository filmRepository, UserRepository userRepository,
                          FilmService filmService) {
        super(modelMapper, Order.class, OrderDTO.class);
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.filmService = filmService;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(m -> m.skip(OrderDTO::setUserId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDTO::setFilmId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(OrderDTO::setFilmDTO)).setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(OrderDTO.class, Order.class)
                .addMappings(m -> m.skip(Order::setFilm)).setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(OrderDTO source, Order destination) {
        destination.setFilm(filmRepository.findById(source.getFilmId())
                .orElseThrow(() -> new NotFoundException("Фильм не найден")));
        destination.setUser(userRepository.findById(source.getUserId())
                .orElseThrow(() -> new NotFoundException("Пользователь не найден")));
    }

    @Override
    protected void mapSpecificFields(Order source, OrderDTO destination) {
        destination.setUserId(source.getUser().getId());
        destination.setFilmId(source.getFilm().getId());
        destination.setFilmDTO(filmService.getOne(source.getFilm().getId()));
    }
}

