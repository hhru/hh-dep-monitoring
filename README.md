### Мониторинг HH проектов

[![Build Status](https://travis-ci.org/hhru/hh-dep-monitoring.svg?branch=master)](https://travis-ci.org/hhru/hh-dep-monitoring) 
[![codecov](https://codecov.io/gh/hhru/hh-dep-monitoring/branch/master/graph/badge.svg)](https://codecov.io/gh/hhru/hh-dep-monitoring)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ru.hh.school%3Ahh-dep-monitoring&metric=alert_status)](https://sonarcloud.io/dashboard?id=ru.hh.school%3Ahh-dep-monitoring)

_Учебный проект школы программистов_

Собирать: `mvn clean install`

Запускать: `mvn exec:java`

Запустить фронтенд `mvn -P frontend clean test frontend:yarn@yarn-start`


Настройка Docker на ubuntu. 
   `https://docs.docker.com/install/linux/linux-postinstall/`

Как поднять  приложение с помощью  Docker Compose

    1) Собираем докер образ сервиса. В папке hh-dep-monitoring-service запускаем команду
	     mvn -P docker clean install

    2) Собираем фронтенд. В папке frontend запускаем команду
	    yarn build

    3) Поднимаем приложение.     
    В файле service.property должна быть строка
        master.jdbcUrl=jdbc:postgresql://postgres-monitoring/monitoring?useUnicode=true&characterEncoding=UTF8
    Не забудте раскомментировать github.oauth.    
    В папке hh-dep-monitoring запускаем команду
	    docker-compose up

Как поднять dev окружение(только nginx и БД)

    1) Помещаем в переменную окружения адрес хоста на котором запущен контейнер.
        В терминале 
        export DOCKERHOST=$(ifconfig | grep -E "([0-9]{1,3}\.){3}[0-9]{1,3}" | grep -v 127.0.0.1 | awk '{ print $2 }' | cut -f2 -d: | head -n1)
        
    2) В том-же окне терминала в папке hh-dep-monitoring запускаем команду
	    docker-compose -f dev-compose.yml up
