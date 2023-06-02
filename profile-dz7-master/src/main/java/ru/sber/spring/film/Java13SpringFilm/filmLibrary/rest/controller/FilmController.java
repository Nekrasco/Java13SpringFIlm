package ru.sber.spring.film.Java13SpringFilm.filmLibrary.rest.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.DirectorDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.FilmDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Film;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.DirectorService;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.service.FilmService;


@RestController
@RequestMapping("/films")
@Tag(name = "Фильмы", description = "Контроллер для работы с фильмами")
public class FilmController extends GenericController<Film, FilmDTO> {

    private final FilmService filmService;
    private final DirectorService directorService;

    public FilmController(FilmService filmService, DirectorService directorService) {
        super(filmService);
        this.filmService = filmService;
        this.directorService = directorService;
    }

    @Operation(description = "Добавить директора к фильму", method = "addDirector")
    @RequestMapping(value = "/addDirector", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDTO> addDirector(@RequestParam(value = "filmId") Long filmId,
                                               @RequestParam(value = "directorId") Long directorId) {
        FilmDTO filmDTO = filmService.getOne(filmId);
        DirectorDTO directorDTO = directorService.getOne(directorId);
        filmDTO.getDirectorsIds().add(directorDTO.getId());
        return ResponseEntity.status(HttpStatus.OK).body(filmService.update(filmDTO));
    }
}


