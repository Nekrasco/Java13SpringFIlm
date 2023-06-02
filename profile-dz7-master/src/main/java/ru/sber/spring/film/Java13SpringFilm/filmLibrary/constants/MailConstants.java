package ru.sber.spring.film.Java13SpringFilm.filmLibrary.constants;

public interface MailConstants {
    String MAIL_MESSAGE_FOR_REMEMBER_PASSWORD = """
                    Добрый день. Вы получили это письмо, так как с вашего аккаунта была отправлена заявка на восстановление пароля.
                    Для восстановления пароля перейдите по ссылке: http://localhost:9090/users/change-password?uuid=""";

    String MAIL_SUBJECT_FOR_REMEMBER_PASSWORD = "Восстановление пароля на сайте Онлайн Фильмотека";
}
