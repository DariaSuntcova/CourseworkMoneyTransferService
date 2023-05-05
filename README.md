# Курсовой проект «Сервис перевода денег»

## Описание проекта

REST-сервис. Сервис должен предоставить интерфейс для перевода денег с одной карты на другую по заранее описанной спецификации(https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml)

Работает с заранее подготовленным FRONT по адресу https://serp-ya.github.io/card-transfer/
## Требования к приложению

1. Сервис должен предоставлять REST-интерфейс для интеграции с FRONT.
2. Сервис должен реализовывать все методы перевода с одной банковской карты на другую, описанные в протоколе.
3. Все изменения должны записываться в файл — лог переводов в произвольном формате с указанием:
- даты; 
- времени; 
- карты, с которой было списание; 
- карты зачисления; 
- суммы; 
- комиссии; 
- результата операции, если был.

## Требования в реализации

1. Приложение разработано с использованием Spring Boot. 
2. Использован сборщик пакетов gradle/maven. 
3. Для запуска используется Docker, Docker Compose . 
4. Код размещён на GitHub. 
5. Код покрыт юнит-тестами с использованием mockito. 
6. Добавлены интеграционные тесты с использованием testcontainers.

## Запуск приложения

Запуск производится с помощью Docker Compose

- docker-compose up -d

Для совместимости с FRONT приложение работает на порту 5500

## Примеры запросов


```
POST http://localhost:5500/transfer
Content-Type: application/json;charset=UTF-8

{
 "cardFromNumber" : "1234567890111213",
 "cardToNumber": "2222222222222222",
 "cardFromCVV": "456",
 "cardFromValidTill": "01/25",
 "amount": {
   "currency": "RUR",
   "value": 15000
    }
}

#####

POST http://localhost:5500/confirmOperation
Content-Type: application/json;charset=UTF-8

{
  "code": "0000",
  "operationId": "1"
}
```
