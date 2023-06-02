package ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Director;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Film;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Genre;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class FilmDTO extends GenericDTO {

    private String title;

    private LocalDate premierYear;

    private String country;

    private Genre genre;

    private Set<Long> directorsIds;

    private Set<Long> ordersIds;

    private boolean isDeleted;

    public FilmDTO(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle(film.getTitle());
        filmDTO.setPremierYear(film.getPremierYear());
        filmDTO.setCountry(film.getCountry());
        filmDTO.setGenre(film.getGenre());
        Set<Director> directors = film.getDirector();
        Set<Long> directorIds = new HashSet<>();
        if (directors != null && directors.size() > 0) {
            directors.forEach(a -> directorIds.add(a.getId()));
        }
        filmDTO.setDirectorsIds(directorIds);
    }
    public <E> FilmDTO(String title1, String premierYear1, String country1, int i, Genre drama, HashSet<E> es, HashSet<E> es1, boolean b) {
    }

    public <E> FilmDTO(String filmTitle2, String publishDate2, int i, int i1, String storagePlace2, String onlineCopyPath2, String publish2, String description2, Genre novel, HashSet<E> es, boolean b) {
    }
}


