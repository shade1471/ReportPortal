*****Инструкция по минимальной настройке и первому запуску ReportPortal*****

***Установка ReportPortal***
1. Взять готовый проект для которого планируется интеграция с Report Portal
2. Имея установленный Docker Dеsktop, перейти по ссылке для скачивания или копирования [docker-compose](https://github.com/reportportal/reportportal/blob/master/docker-compose.yml).
3. В ситуации если Docker на Windows, расскомитить подключение томов в docker-compose для postgresql. [Инструкция](https://reportportal.io/docs/Deploy-ReportPortal-with)
```
    volumes:
   # For windows host
    - postgres:/var/lib/postgresql/data
   # For unix host
   # - ./data/postgres:/var/lib/postgresql/data

   # Docker volume for Windows host
   volumes:
     postgres:
```
4. Запустить docker-compose коммандой
   ```docker-compose -p reportportal up -d --force-recreate```
5. Открыть в браузере ReportPortal [http://localhost:8080/](http://localhost:8080/)
6. Авторизоваться под администратором, данные для авторизации
```
Логин superadmin
Пароль erebus
```
7. Снизу, кликнуть на профиль администратора перейти в режим администрирования, с левой стороны выбрать раздел пользователей. Добавить пользователя, добавив логин и пароль.
8. Выйти из под администратора и авторизоваться под созданным пользователем.
9. Кликнуть слева по значку пользователь, перейти в настройки профиля. Где справа будет указаны данные для REPORTPORTAL.PROPERTIES файла, который необходимо будет положить в каталог resources в вашем проекте.

**Что необходимо добавить в проекте в IDE в случае использования Junit5:**
1. Перейти по [ссылке](https://github.com/reportportal/examples-java/tree/master/example-junit5/src/test/resources), откуда взять файлы ```logback.xml``` , ```junit-platform.properties``` ,  каталог META-INF/services и файлом ```org.junit.jupiter.api.extension.Extension```
2. Добавить данные файлы себе в проект, сохраняя структуру как в примере по ссылке выше.
3. Файл ```reportportal.properties``` с его внутренним содержимым берется из шага ```9``` установки ReportPortal
4. Добавить зависимости для проекта, в случаи использования Graddle в build.gradle в блоке ```dependencies```  добавить:
```
    implementation 'com.epam.reportportal:agent-java-junit5:5.0.0'
    implementation 'com.epam.reportportal:logger-java-logback:5.0.2'
    implementation 'ch.qos.logback:logback-classic:1.2.3'
    implementation 'com.epam.reportportal:logger-java-log4j:5.0.2'
    compileOnly 'log4j:log4j:1.2.17'
    implementation 'org.apache.logging.log4j:log4j-api:2.13.3'
    implementation 'org.apache.logging.log4j:log4j-core:2.13.3'
```

***Запуск тестов и просмотр их в ReportPortal***
1. Запустить тесты в IDE
2. Открыть в браузере ReportPortal [http://localhost:8080/](http://localhost:8080/)
3. Если не авторизованы, то авторизоваться под созданным пользователем (шаг ```7``` установки ReportPortal)
4. С левой стороны перейти в задел ```LAUNCHES```, где будут отображены проведенные тесты. По каждому из которых можно кликнуть и посмотреть логи.
