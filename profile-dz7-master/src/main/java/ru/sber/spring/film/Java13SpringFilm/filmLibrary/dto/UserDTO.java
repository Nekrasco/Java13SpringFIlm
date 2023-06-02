package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO extends GenericDTO {

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String middleName;

    private String birthDate;

    private String phone;

    private String address;

    private String email;

    private LocalDate createdWhen2;

    private RoleDTO role;

    private Set<Long> ordersIds;

    private String changePasswordToken;

    private boolean isDeleted;

    public UserDTO(String login, String password, String email, String birthDate, String firstName, String lastName, String middleName, String phone, String address, RoleDTO roleDTO, String changePasswordToken, HashSet<Object> objects, boolean b) {
    }
}
