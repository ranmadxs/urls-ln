# urls-ln
Transforma url short a url original y redirecciona.

## Compilar

```
./mvnw clean install
```
## Desplegar

```
java -jar target/urls-ln.jar
```
## Modo de uso
Para ejecutar en la "nube" abrir un browser y tipear:
https://urls-ln.herokuapp.com/<id>
Por ejemplo: 
https://urls-ln.herokuapp.com/62b9539d070b6e000480f071

Documentación "viva":
https://urls-ln.herokuapp.com/doc



## Heroku

Para revisar los logs y efectuar "escalado manual", el automático es de pago.
```
heroku logs --tail --app urls-ln

heroku ps:scale web=2 --app urls-ln
```

