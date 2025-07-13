CHANGE EMAIL AT EmailSrvice.java WHERE YOU WANT TO SEND MAILS

CHANGE aplication.properties MAIL AND DB IF YOU NEED

– Взяти попереднє ДЗ з автомобілями

1. Додати поле з фото автівки.

2. Додаємо відправку листа на пошту при збереженні авто в бд. В листі відправляти дані про те, яке авто збережене

3. Те саме, але при видаленні

 ---

– Створити модель та реалізувати операції, використовуючи mongodb

Car

    id

    model

    producer

    power

 

реалізувати запити

get /cars

get /cars/{id}

post /cars

– реалізувати логіку очищення бд автівок від об’єктів, в яких power < 100 сил

 

 

– 

delete /cars/{id}
