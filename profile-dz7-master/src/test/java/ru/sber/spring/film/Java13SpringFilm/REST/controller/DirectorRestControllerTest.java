package ru.sber.spring.film.Java13SpringFilm.REST.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.AddFilmsDTO;
import ru.sber.spring.film.Java13SpringFilm.filmLibrary.dto.DirectorDTO;


import java.util.HashSet;
import java.util.List;


import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class DirectorRestControllerTest
        extends CommonTestREST {

    private static Long createdAuthorID;

    @Test
    @Order(0)
    protected void listAll() throws Exception {
        log.info("Тест по просмотра всех режиссеров через REST начат успешно");
        String result = mvc.perform(
                        get("/rest/directors/getAll")
                                .headers(headers)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<DirectorDTO> directorDTOS = objectMapper.readValue(result, new TypeReference<List<DirectorDTO>>() {});
        directorDTOS.forEach(a -> log.info(a.toString()));
        log.info("Тест по просмотра всех режиссеров через REST закончен успешно");
    }

    @Test
    @Order(1)
    protected void createObject() throws Exception {
        log.info("Тест по созданию режиссера через REST начат успешно");
        //Создаем нового автора для создания через контроллер (тест дата)
        DirectorDTO directorDTO = new DirectorDTO("REST_TestDirectorFio", "2023-01-01", "Test Description", new HashSet<>(), false);

        /*
        Вызываем метод создания (POST) в контроллере, передаем ссылку на REST API в MOCK.
        В headers передаем токен для авторизации (под админом, смотри родительский класс).
        Ожидаем, что статус ответа будет успешным и что в ответе есть поле ID, а далее возвращаем контент как строку
        Все это мы конвертируем в AuthorDTO при помощи ObjectMapper от библиотеки Jackson.
        Присваиваем в статическое поле ID созданного автора, чтобы далее с ним работать.
         */
        DirectorDTO result = objectMapper.readValue(mvc.perform(post("/rest/directors/add")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .headers(super.headers)
                                .content(asJsonString(directorDTO))
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                DirectorDTO.class);
        createdAuthorID = result.getId();
        log.info("Тест по созданию режиссера через REST закончен успешно " + result);
        /*
        можно запустить один тест и по цепочке вызывать остальные:
        updateAuthor(createdAuthorID);
         */
    }

    @Test
    @Order(2)
    protected void updateObject() throws Exception {
        log.info("Тест по обновления режиссера через REST начат успешно");
        //получаем нашего автора созданного (если запускать тесты подряд), если отдельно - создаем отдельную тест дату для апдейта
        DirectorDTO existingAuthor = objectMapper.readValue(mvc.perform(get("/rest/directors/getOneById")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .headers(super.headers)
                                .param("id", String.valueOf(createdAuthorID))
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                DirectorDTO.class);
        //обновляем поля
        existingAuthor.setDirectorFio("REST_TestAuthorFioUPDATED");
//        existingAuthor.set("Test Description UPDATED");

        //вызываем update через REST API
        mvc.perform(put("/rest/directors/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .headers(super.headers)
                        .content(asJsonString(existingAuthor))
                        .param("id", String.valueOf(createdAuthorID))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        log.info("Тест по обновления режиссера через REST закончен успешно");
    }

    @Test
    @Order(3)
    void addBook() throws Exception {
        log.info("Тест по добавлению фильма к режиссеру через REST начат успешно");
        AddFilmsDTO addFilmDTO = new AddFilmsDTO(11L, createdAuthorID);
        String result = mvc.perform(post("/rest/directors/addFilm")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .headers(super.headers)
                        .content(asJsonString(addFilmDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DirectorDTO director = objectMapper.readValue(result, DirectorDTO.class);
        log.info("Тест по добавлению  фильма к режиссеру через REST завершен успешно с результатом {}",
                director);
    }

    @Test
    @Order(4)
    protected void deleteObject() throws Exception {
        log.info("Тест по удалению режиссера через REST начат успешно");
        mvc.perform(delete("/rest/directors/delete/{id}", createdAuthorID)
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        DirectorDTO existingAuthor = objectMapper.readValue(mvc.perform(get("/rest/directors/getOneById")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .headers(super.headers)
                                .param("id", String.valueOf(createdAuthorID))
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().is2xxSuccessful())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(),
                DirectorDTO.class);

        assertTrue(existingAuthor.isDeleted());
        log.info("Тест по удалению режиссера через REST завершен успешно");
        mvc.perform(
                        delete("/rest/directors/delete/hard/{id}", createdAuthorID)
                                .headers(headers)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        log.info("Данные очищены");
    }

}