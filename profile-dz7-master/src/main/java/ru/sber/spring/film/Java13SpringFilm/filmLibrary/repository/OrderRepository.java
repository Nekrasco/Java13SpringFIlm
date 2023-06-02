package ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Order;


@Repository
public interface OrderRepository extends GenericRepository<Order> {
    Page<Order> getOrderByUserId(Long userId, Pageable pageable);
}
