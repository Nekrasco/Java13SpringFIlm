package ru.sber.spring.film.Java13SpringFilm;


import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.RoleDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.UserDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Role;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public interface UserTestData {

    UserDTO USER_DTO = new UserDTO("login",
            "password",
            "email",
            "birthDate",
            "firstName",
            "lastName",
            "middleName",
            "phone",
            "address",
            new RoleDTO(),
            "changePasswordToken",
            new HashSet<>(),
            false
    );

    List<UserDTO> USER_DTO_LIST = List.of(USER_DTO);

    User USER = new User("login",
            "password",
            "email",
            LocalDate.now(),
            "firstName",
            "lastName",
            "middleName",
            "phone",
            "address",
            "changePasswordToken",
            new Role(),
            new HashSet<>()
    );

    List<User> USER_LIST = List.of(USER);
}
