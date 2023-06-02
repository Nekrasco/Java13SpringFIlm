package ru.sber.spring.film.Java13SpringFilm.filmLibrary.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.DirectorDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.FilmDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Director;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.DirectorService;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.FilmService;


@RestController
@RequestMapping("/directors")
@Tag(name = "Директора", description = "Контроллер для работы с директорами  фильмов")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DirectorController extends GenericController<Director, DirectorDTO> {

    private final DirectorService directorService;
    private final FilmService filmService;

    public DirectorController(DirectorService directorService, FilmService filmService) {
        super(directorService);
        this.directorService = directorService;
        this.filmService = filmService;
    }

    @Operation(description = "Добавить фильм к директору", method = "addFilm")
    @RequestMapping(value = "/addFilm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DirectorDTO> addFilm(@RequestParam(value = "filmId") Long filmId,
                                               @RequestParam(value = "directorId") Long directorId) {
        DirectorDTO directorDTO = directorService.getOne(directorId);
        FilmDTO filmDTO = filmService.getOne(filmId);
        directorDTO.getFilmsIds().add(filmDTO.getId());
        return ResponseEntity.status(HttpStatus.OK).body(directorService.update(directorDTO));
    }
}


