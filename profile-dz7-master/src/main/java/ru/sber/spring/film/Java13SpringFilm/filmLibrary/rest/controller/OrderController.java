package ru.sber.spring.film.Java13SpringFilm.filmLibrary.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.FilmDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.OrderDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.UserDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Order;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.FilmService;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.OrderService;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.UserService;


@RestController
@RequestMapping("/orders")
@Tag(name = "Заказы", description = "Контроллер для работы с заказами")
public class OrderController extends GenericController<Order, OrderDTO> {

    private final OrderService orderService;
    private final FilmService filmService;
    private final UserService userService;

    public OrderController(OrderService orderService, FilmService filmService, UserService userService) {
        super(orderService);
        this.orderService = orderService;
        this.filmService = filmService;
        this.userService = userService;
    }

    @Operation(description = "Взять фильм в аренду", method = "create")
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO newEntity) {
        FilmDTO filmDTO = filmService.getOne(newEntity.getFilmId());
        UserDTO userDTO = userService.getOne(newEntity.getUserId());
        filmDTO.getOrdersIds().add(newEntity.getId());
        userDTO.getOrdersIds().add(newEntity.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(newEntity));
    }
}

