package ru.sber.spring.film.Java13SpringFilm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Java13SpringFilmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Java13SpringFilmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ура! Заработало!");

	}
}

