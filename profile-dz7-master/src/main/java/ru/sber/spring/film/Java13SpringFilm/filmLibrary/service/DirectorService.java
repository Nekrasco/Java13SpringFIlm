package ru.sber.spring.film.Java13SpringFilm.filmLibrary.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.constants.Errors;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.DirectorDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.DirectorWithFilmsDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.IdFilmDirectorDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.exception.MyDeleteException;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.mapper.DirectorMapper;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.mapper.DirectorWithFilmsMapper;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Director;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.model.Film;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.repository.DirectorRepository;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class DirectorService extends GenericService<Director, DirectorDTO> {
    private final DirectorRepository directorRepository;
    private final FilmService filmService;

    protected DirectorService(DirectorRepository directorRepository, DirectorMapper directorMapper, FilmService filmService) {
        super(directorRepository, directorMapper);
        this.directorRepository = directorRepository;
        this.filmService = filmService;
    }

    public Page<DirectorDTO> listAllNotDeletedDirectors(Pageable pageable) {
        Page<Director> directors = directorRepository.findAllByIsDeletedFalse(pageable);
        List<DirectorDTO> result = mapper.toDTOs(directors.getContent());
        return new PageImpl<>(result, pageable, directors.getTotalElements());
    }

    public Page<DirectorDTO> searchDirectors(final String fio, Pageable pageable) {
        Page<Director> directors = directorRepository.findAllByDirectorsFioContainsIgnoreCaseAndIsDeletedFalse(fio, pageable);
        List<DirectorDTO> result = mapper.toDTOs(directors.getContent());
        return new PageImpl<>(result, pageable, directors.getTotalElements());
    }

    public void addFilm(IdFilmDirectorDTO idFilmDirectorDTO) {
        DirectorDTO directorDTO = getOne(idFilmDirectorDTO.getDirectorId());
        filmService.getOne(idFilmDirectorDTO.getDirectorId());
        directorDTO.getFilmsIds().add(idFilmDirectorDTO.getFilmId());
        update(directorDTO);
    }

    @Override
    public void delete(Long objectId) throws MyDeleteException {
        Director director = directorRepository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Режиссёра с заданным id=" + objectId + " не существует"));
        boolean directorCanBeDeleted = directorRepository.checkDirectorForDeletion(objectId);
        if (directorCanBeDeleted) {
            markAsDeleted(director);
            Set<Film> films = director.getFilms();
            if (films != null && films.size() > 0) {
                films.forEach(this::markAsDeleted);
            }
            directorRepository.save(director);
        }
        else {
            throw new MyDeleteException(Errors.Directors.DIRECTOR_DELETE_ERROR);
        }
    }

    public void restore(Long objectId) {
        Director director = directorRepository.findById(objectId).orElseThrow(
                () -> new NotFoundException("Режиссёра с заданным id=" + objectId + " не существует"));
        unMarkAsDeleted(director);
        Set<Film> films = director.getFilms();
        if (films != null && films.size() > 0) {
            films.forEach(this::unMarkAsDeleted);
        }
        directorRepository.save(director);
    }
}

