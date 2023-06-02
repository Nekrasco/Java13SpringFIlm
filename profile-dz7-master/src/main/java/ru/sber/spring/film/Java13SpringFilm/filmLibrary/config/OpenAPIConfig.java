package ru.sber.spring.film.Java13SpringFilm.filmLibrary.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenAPIConfig {

    @Bean
    public OpenAPI filmLibraryProject(){
        return new OpenAPI()
                .info(new Info()
                .title("Онлайн Фильмотека")
                .description("Сервис, позволяющий арендовать фильм в онлайн фильмотеке")
                .version("v0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                .contact(new Contact().name("Ilya Vasilev").email("vs@mail.ru").url("")));
    }

}
