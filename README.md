Створити модель

Car

    id

    model

    producer

    power


---

реалізувати запити

get /cars

get /cars/{id}

post /cars

delete /cars/{id}

get cars/power/{value} (знайти всі по потужності) ()

get cars/producer/{value} (знайти всі по виробнику)

---

Зробити валідацію полів всіх полів і відповідні обробники

Переробити всі методи контролера, щоб повертати response entity з відповідними статусами

 

винести логіку в сервісний рівень

 

Зробити 3 рівні відображення

Level1 – id model producer power (для endpoint /cars/{id})

Level2 – model producer power ( для endpoint /cars/power, /cars/producer)

Level2 – model producer (для endpoint /cars)
