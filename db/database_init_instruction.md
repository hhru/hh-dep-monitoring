0) Установка на машину docker.      
1) Разворачивание docker контейнера с postgreSQL.   
    1.1 скачивание в локальный репозиторий образ posqres'a(можно пропустить).
             docker pull suhoyvasya/postgres-monitoring       
        
    1.2 разворачиваем контейнер и открываем во вне порт 5432 по которому будет доступна БД.
            docker run -p 5432:5432 -d suhoyvasya/postgres-monitoring
        
    1.3 Узнаем адрес docker-machine(по умолчанию 192.168.99.100)        
            docker-machine ip default          
        
2) Подключение к базе данных
           psql -h 192.168.99.100 -p 5432 -U monitoring  
    
           