# ProjectMVC--hibernate
12.01.2025
Подключен Spring Data JPA (до этого был Hibernate( До этого было (JDBC Template)), в конфиге добавили 2 метода по настройке сешнфабрики.
Созданы слои service и repository.
Написаны кастомные методы в сервисном слое.
dao класс не удален, пусть для примера будет, в этом классе можно писать сложные запросы.

Добавлена еще одна entity Item, связь по внешнему ключу с Person.
В Person добавлены поля дата рождения и дата создания заказа, и состояние (enum))


Описание
Проект запускается через ТомКат 11
http://localhost:8080/people

Технологии, библиотеки и frameworks:
сборка Maven
Java 17
Spring 6
Spring core
Spring context - спринг контейнер
Spring web
Spring webMVC
Spring-jdbc(JDBC Template)
Hibernate- связь с БД
spring-data-jpa - связь с БД
postgresql 
Thymeleaf - шаблонизатор html страниц
Hibernate-validator
jakarta (для валидации)


PostgreSQL
name - postgres
password - postgres
порт 5432
jdbc:postgresql://localhost:5432/projectmvc_db


Функционал REST API c CRUD операциями:
Вывести список
- - http://localhost:8080/people

Показать информацию по id
- - http://localhost:8080/people/2

Создание нового
- - http://localhost:8080/people/new

Редактирование
- - http://localhost:8080/people/2/edit

Удаление

Реализована валидация данных при создании и редактировании

Описание функционала:
Вывести полный список людей
- http://localhost:8080/people
Создать нового-
На этой странице активная кнопка -Create new people
При нажатии переходит  на страницу с формой для создания 
- http://localhost:8080/people/new
При нажатии на кнопку - Create - если данные прошли валидацию,то сохраняются и  происходит переход на страницу со списком
Если не валидные данные -  будет выведено на странице под полем описание ошибки и можно ввести заново

При нажатии на персону выводит всю инфу по id
- http://localhost:8080/people/2
  на этой же странице есть кнопка  -  edit(редактировать) и delete(удалить)
При нажатии - edit - переходит на страницу с формой для редактирования
- http://localhost:8080/people/2/edit
Здесь после изменения данных при нажатии кнопки - update - сохраняются данные и переходит на страницу со списком людей 
- http://localhost:8080/people

При удалении - переходит на страницу со списком
