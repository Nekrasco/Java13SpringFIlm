package ru.sber.spring.film.Java13SpringFilm.filmLibrary.model;

public enum Genre {
    DRAMA("Драма"),
    COMEDY("Комедия"),
    THRILLER("Триллер"),
    FANTASTIC("Фантастика"),
    SCIENTIFIC("Научный"),
    HORROR("Ужасы"),
    ACTION("Боевик"),
    HISTORICAL("Исторический");

    private final String genreDisplayValue;

    Genre(String text){
        this.genreDisplayValue = text;
    }

    public String getGenreDisplayValue() {
        return this.genreDisplayValue;
    }
}
