package ru.sber.spring.film.Java13SpringFilm.filmLibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.FilmDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.OrderDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.mapper.OrderMapper;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Order;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService extends GenericService<Order, OrderDTO> {
    private final FilmService filmService;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    protected OrderService(OrderRepository orderRepository, OrderMapper orderMapper, FilmService filmService) {
        super(orderRepository, orderMapper);
        this.filmService = filmService;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public Page<OrderDTO> listUserRentFilms(final Long id, final Pageable pageable) {
        Page<Order> objects = orderRepository.getOrderByUserId(id, pageable);
        List<OrderDTO> results = orderMapper.toDTOs(objects.getContent());
        return new PageImpl<>(results, pageable, objects.getTotalElements());
    }

    public OrderDTO rentFilm(OrderDTO orderDTO) {
        FilmDTO filmDTO = filmService.getOne(orderDTO.getFilmId());
        long rentPeriod = orderDTO.getRentPeriod() != null ? orderDTO.getRentPeriod() : 14L;
        orderDTO.setRentDate(LocalDate.now());
        orderDTO.setRentPeriod((int) rentPeriod);
        orderDTO.setIsPurchased(false);
        return orderMapper.toDTO(repository.save(orderMapper.toEntity(orderDTO)));
    }
}
