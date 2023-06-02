package ru.sber.spring.film.Java13SpringFilm.filmLibrary.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "orders_seq", allocationSize = 1)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Order extends GenericModel{

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDERS_USERS"))
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "film_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDERS_FILMS"))
    private Film film;

    @Column(name = "rent_date", nullable = false)
    private LocalDate rentDate;

    @Column(name = "rent_period", nullable = false)
    private String rentPeriod;

    @Column(name = "purchase", nullable = false)
    private boolean isPurchase;

    public Order(Object o, Object o1, LocalDateTime now, LocalDateTime now1, boolean b, int i) {
    }

}
