## [REST API](http://localhost:8080/doc)

## Концепция:
- Spring Modulith
  - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
  - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
  - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```
- Есть 2 общие таблицы, на которых не fk
  - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
  - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем проверять

## Аналоги
- https://java-source.net/open-source/issue-trackers

## Тестирование
- https://habr.com/ru/articles/259055/

## List of completed tasks:
1. (+) Understand the project structure (onboarding).
2. (+) Delete social networks: vk, yandex. Easy task
   ![alt text](https://github.com/Gluk87/project-final/blob/dev/img/screen2.png)
3. (+) Transfer sensitive information (login, database password, identifiers for OAuth registration/authorization, mail settings) to a separate property file. The values of these properties must be read at server startup from the environment variables of the machine. Easy task
   ![alt text](https://github.com/Gluk87/project-final/blob/dev/img/screen3.png)
4. (+) Redo the tests so that during the tests the in memory database (H2) is used, and not PostgreSQL. To do this, you need to define 2 beans, and the selection of which one to use should be determined by the active Spring profile.
   ![alt text](https://github.com/Gluk87/project-final/blob/dev/img/screen4.png)
5. (+) Write tests for all public methods of the ProfileRestController controller.
   ![alt text](https://github.com/Gluk87/project-final/blob/dev/img/screen5.png)
6. (+) Add new functionality: adding tags to the task. The front is optional.
7. (+) Add the ability to subscribe to tasks that are not assigned to the current user. (There is no need to send notifications/letters about the task status change). The front is optional.
8. (+) Add automatic calculation of the time how long the task was in work and testing. Write 2 methods on the service levels that take a task as a parameter and return the elapsed time.
   ![alt text](https://github.com/Gluk87/project-final/blob/dev/img/screen8.png)
9. Write a Dockerfile for the main server
   ![alt text](https://github.com/Gluk87/project-final/blob/dev/img/screen9.png)
10. Написать docker-compose файл для запуска контейнера сервера вместе с БД и nginx. Для nginx используй конфиг-файл config/nginx.conf. При необходимости файл конфига можно редактировать. Hard task
11. Добавить локализацию минимум на двух языках для шаблонов писем и стартовой страницы index.html.
12. Реализовать бэклог (backlog) – полный список задач (с пейджингом), которые должны быть выполнены и еще не относятся ни к какому спринту. (бек + фронт). Hard task
13. Переделать механизм распознавания «свой-чужой» между фронтом и беком с JSESSIONID на JWT. Из сложностей – тебе придётся переделать отправку форм с фронта, чтоб добавлять хедер аутентификации. Hard task
