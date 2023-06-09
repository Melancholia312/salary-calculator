# Калькулятор отпускных

Приложение "Калькулятор отпускных".
Микросервис на SpringBoot + Java 11 c одним API:
GET "/calculacte"

Приложение принимает твою среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает суммой отпускных, которые придут сотруднику.
При запросе также можно указать точные дни ухода в отпуск, тогда должен проводиться рассчет отпускных с учётом праздников и выходных.

**Пример запроса с количеством дней отпуска:**

```
http://localhost:8080/calculate?salary=1000&vacationDays=100
```

Где salary - заплата за 12 месяцев, 
а vacationDays - количество отпускных дней


**Пример запроса с точной датой ухода в отпуск:**

```
http://localhost:8080/calculate?salary=1000&startDate=2023-05-01&endDate=2023-06-01
```

Где salary - заплата за 12 месяцев, 
startDate - дата начала отпуска,
а endDate - конец
