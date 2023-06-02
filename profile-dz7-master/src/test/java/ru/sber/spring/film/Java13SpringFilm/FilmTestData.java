package ru.sber.spring.film.Java13SpringFilm;



import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.DirectorDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.FilmDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.FilmWithDirectorsDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Film;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Genre;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface FilmTestData {
    FilmDTO BOOK_DTO_1 = new FilmDTO(
            "title1",
            "premierYear1",
            "country1",
            1,
            Genre.DRAMA,
            new HashSet<>(),
            new HashSet<>(),
            false);

    FilmDTO BOOK_DTO_2 = new FilmDTO("filmTitle2",
            "publishDate2",
            2,
            2,
            "storagePlace2",
            "onlineCopyPath2",
            "publish2",
            "description2",
            Genre.ACTION,
            new HashSet<>(),
            false);

    List<FilmDTO> BOOK_DTO_LIST = Arrays.asList(BOOK_DTO_1, BOOK_DTO_2);

    Film FILM_1 = new Film("filmTitle1",
            LocalDate.now(),
            1,
            1,
            "publish1",
            "storagePlace1",
            "onlineCopyPath1",
            "description",
            Genre.DRAMA,
            new HashSet<>(),
            new HashSet<>());
    Film FILM_2 = new Film("filmTitle2",
            LocalDate.now(),
            2,
            2,
            "publish2",
            "storagePlace2",
            "onlineCopyPath2",
            "description2",
            Genre.ACTION,
            new HashSet<>(),
            new HashSet<>());

    List<Film> FILM_LIST = Arrays.asList(FILM_1, FILM_2);

    Set<DirectorDTO> AUTHORS = new HashSet<>(DirectorTestData.AUTHOR_DTO_LIST);
    FilmWithDirectorsDTO BOOK_WITH_AUTHORS_DTO_1 = new FilmWithDirectorsDTO(FILM_1, AUTHORS);
    FilmWithDirectorsDTO BOOK_WITH_AUTHORS_DTO_2 = new FilmWithDirectorsDTO(FILM_2, AUTHORS);

    List<FilmWithDirectorsDTO> BOOK_WITH_AUTHORS_DTO_LIST = Arrays.asList(BOOK_WITH_AUTHORS_DTO_1, BOOK_WITH_AUTHORS_DTO_2);
}
