package ru.sber.spring.film.Java13SpringFilm;


import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.OrderDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderTestData {

    OrderDTO BOOK_RENT_INFO_DTO = new OrderDTO(LocalDateTime.now(),
            LocalDateTime.now(),
            false,
            14,
            1L,
            1L,
            null);

    List<OrderDTO> BOOK_RENT_INFO_DTO_LIST = List.of(BOOK_RENT_INFO_DTO);

    Order BOOK_RENT_INFO = new Order(null,
            null,
            LocalDateTime.now(),
            LocalDateTime.now(),
            false,
            14);

    List<Order> BOOK_RENT_INFO_LIST = List.of(BOOK_RENT_INFO);
}