Взяти попереднє дз, та додати до нього рівень сек’юріті.

Рівні доступу

admin – може все 

manager – може додавати, але не видаляти

user – може тільки отримувати дані

---

Register:
  -api/v1/auth/register
  
  Body:
  
    {
      "firstName": "...",
      "lastName": "...",
      "email": "asd@asd.com",
      "password": "...",
      "role": "..." (ADMIN, MANAGER, USER)
    }
    
  Response:
  
    {
      "token": "...",
      "refreshToken": "..."
    }

---

Authentication:

  -api/v1/auth/authenticate
  Body:
  
    {
      "email":"asd@asd.com",
      "password":"..."
    }
    
Response:

     {
      "token": "...",
      "refreshToken": "..."
    }

---

RefreshTokens:

  -api/v1/auth/refresh
  Body:
  
    {
      "refreshToken": "..."
    }
    
  Response:
  
    {
      "token": "...",
      "refreshToken": "..."
    }

---

GetRequests:

  Users:
  
    -api/v1/users          (ALL authorized)
    -api/v1/users/{id}     (ALL authorized)
    
  Cars: 
  
    -api/v1/cars           (All authorized)

DeleteRequests:

  Users:
  
    -api/v1/users/{id}     (Only ADMIN)
    
  Cars:
  
    -api/v1/cars/{id}      (Only ADMIN)  

PostRequests:

  Cars:
  
    -api/v1/cars           (ADMIN, MANAGER)
