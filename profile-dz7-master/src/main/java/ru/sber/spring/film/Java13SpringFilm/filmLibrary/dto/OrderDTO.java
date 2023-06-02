package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO extends GenericDTO{

    private UserDTO user;

    private FilmDTO filmDTO;

    private LocalDate rentDate;

    private Integer rentPeriod;

    private Boolean isPurchased;

    private Long filmId;

    private Long userId;

    public OrderDTO(LocalDateTime now, LocalDateTime now1, boolean b, int i, long l, long l1, Object o) {

    }
    }
