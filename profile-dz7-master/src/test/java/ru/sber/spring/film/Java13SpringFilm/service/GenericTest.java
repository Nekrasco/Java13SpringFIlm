package ru.sber.spring.film.Java13SpringFilm.service;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.GenericDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.exception.MyDeleteException;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.mapper.GenericMapper;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.GenericModel;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository.GenericRepository;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.GenericService;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.userdetails.CustomUserDetails;

public abstract class GenericTest<E extends GenericModel, D extends GenericDTO> {
    protected GenericService<E, D> service;
    protected GenericRepository<E> repository;
    protected GenericMapper<E, D> mapper;

    @BeforeEach
    void init() {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(CustomUserDetails
                .builder()
                .username("USER"),
                null,
                null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    protected abstract void getAll();

    protected abstract void getOne();

    protected abstract void create();

    protected abstract void update();

    protected abstract void delete() throws MyDeleteException;

    protected abstract void restore();

    protected abstract void getAllNotDeleted();
}