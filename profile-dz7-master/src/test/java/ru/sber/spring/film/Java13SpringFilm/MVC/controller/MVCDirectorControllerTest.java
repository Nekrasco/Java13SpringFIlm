package ru.sber.spring.film.Java13SpringFilm.MVC.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.DirectorDTO;

import java.util.HashSet;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class MVCDirectorControllerTest
        extends ru.sber.spring.film.Java13SpringFilm.MVC.controller.CommonTestMVC {


    /**
     * Метод, тестирующий просмотр всех авторов через MVC-контроллер.
     * Ожидаем, что результат ответа будет просто любой 200 статус.
     * Проверяем, что view, на которое нас перенаправит контроллер, при удачном вызове - это как раз показ всех авторов
     * Так-же, ожидаем, что в модели будет атрибут authors.
     *
     * @throws Exception - любая ошибка
     */
    @Test
    @DisplayName("Просмотр всех авторов через MVC контроллер, тестирование 'authors/'")
    @Order(0)
    @WithAnonymousUser
    @Override
    protected void listAll() throws Exception {
        log.info("Тест по выбору всех режиссеров через MVC начат");
        mvc.perform(get("/directors")
                        .param("page", "1")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("directors/viewAllDirectors"))
                .andExpect(model().attributeExists("directors"))
                .andReturn();
    }

    /**
     * Метод, тестирующий создание автора через MVC-контроллер.
     * Авторизуемся под пользователем admin (можно выбрать любого),
     * создаем шаблон данных и вызываем MVC-контроллер с соответствующим маппингом и методом.
     * flashAttr - используется, чтобы передать ModelAttribute в метод контроллера
     * Ожидаем, что будет статус redirect (как у нас в контроллере) при успешном создании
     *
     * @throws Exception - любая ошибка
     */
    @Test
    @DisplayName("Создание режиссера через MVC контроллер, тестирование 'directors/add'")
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    @Override
    protected void createObject() throws Exception {
        log.info("Тест по созданию режиссера через MVC начат успешно");
        //Создаем нового автора для создания через контроллер (тест дата)
        DirectorDTO directorDTO = new DirectorDTO("MVC_TestDirectorFio", "2023-01-01", "Test Description", new HashSet<>(), false);

        mvc.perform(post("/directors/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .flashAttr("directorForm", directorDTO)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/directors"))
                .andExpect(redirectedUrlTemplate("/directors"))
                .andExpect(redirectedUrl("/directors"));
        log.info("Тест по созданию режиссера через MVC закончен успешно");
    }

    @Override
    protected void updateObject() throws Exception {

    }

    @Override
    protected void deleteObject() throws Exception {

    }
}
