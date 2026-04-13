# API Testing Project
Проект для автоматизации тестирования REST API с использованием современного стека технологий.

[![Java Version](https://img.shields.io/badge/Java-17-blue?style=flat-square)](https://adoptium.net/)
[![Rest Assured](https://img.shields.io/badge/Rest%20Assured-5.5.6-brightgreen?style=flat-square)](https://rest-assured.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red?style=flat-square)](https://testng.org/)
[![Allure](https://img.shields.io/badge/Allure-2.32.0-orange?style=flat-square)](https://allurereport.org/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue?style=flat-square&logo=docker)](https://www.docker.com/)

## 📝 Описание
Тестируется публичное API [JSONPlaceholder](https://jsonplaceholder.typicode.com)

**Ключевые возможности:**
- Полный CRUD цикл для ресурса `/users` (GET, POST, PUT, PATCH, DELETE)
- Параметризованные тесты с `DataProvider`
- Fluent assertions с AssertJ
- POJO модели с Lombok и `@Accessors(chain = true)`
- Гибкое логирование через `.log().ifValidationFails()`
- Генерация Allure отчётов
- Контейнеризация через Docker

---

## 🛠 Стек технологий

| Технология | Версия | Назначение |
|------------|--------|------------|
| Java | 17 | Язык программирования |
| Maven | 3.6+ | Сборка проекта и управление зависимостями |
| TestNG | 7.10.2 | Тестовый фреймворк |
| RestAssured | 5.5.6 | Библиотека для тестирования REST API |
| AssertJ | 3.27.7 | Fluent assertions для читаемых проверок |
| Lombok | 1.18.42 | Уменьшение шаблонного кода |
| Allure | 2.32.0 | Фреймворк для генерации отчётов |
| JSON Schema Validator | 5.5.6 | Валидация структуры JSON-ответов |
 Jenkins | 2.x | CI/CD сервер (автоматизация запуска тестов) |
| Docker | 20.10+ | Контейнеризация |

## 🚀 Запуск тестов

### Предварительные требования
- Java 17 или выше
- Maven 3.6 или выше

### Локальный запуск

```bash
# Клонировать репозиторий
git clone https://github.com/AlenaDubakina/API_Rest_Assured_Allure_Jenkins.git

# Перейти в папку проекта
cd API_Rest_Assured_Allure_Jenkins

# Запустить все тесты
mvn clean test

# Запустить тесты и открыть Allure отчёт
mvn clean test
mvn allure:serve
```
## 🐳 Запуск с Docker
Проект поддерживает запуск как самих тестов, так и Jenkins-сервера в контейнерах.

### Предварительные требования

- Установленный Docker и Docker Compose

### Запуск Jenkins в контейнере
```
# Поднять Jenkins сервер
docker-compose up -d

# Jenkins будет доступен по адресу: http://localhost:8081
# Путь для монтирования volumes: ./jenkins_home
```
### Запуск тестов в Docker-контейнере
```
# Собрать образ с тестами
docker build -t api-tests .

# Запустить тесты в контейнере
docker run --rm api-tests
```
### Компоненты Docker в проекте

| Файл                 | Назначение |
|----------------------|------------|
| Dockerfile           | Образ для запуска тестов (Java + Maven + тесты) |
| Dockerfile.jenkins   | Образ для Jenkins с предустановленным Maven и Allure |
| docker-compose.yml   | Оркестрация: поднимает Jenkins на порту 8081 с монтированием volumes |

### Полезные Docker-команды
```
# Посмотреть логи Jenkins
docker-compose logs -f jenkins

# Остановить все контейнеры
docker-compose down

# Остановить и удалить volumes
docker-compose down -v
```

## 🔄 CI/CD

Проект интегрирован с Jenkins для автоматического запуска тестов.

| Инструмент | Назначение |
|------------|------------|
| **Jenkins** | CI/CD сервер |
| **GitHub** | Хранение кода и Jenkinsfile |
| **Allure Jenkins Plugin** | Публикация отчётов в Jenkins |
| **Cron (H 8 * * 1-5)** | Расписание запуска (ежедневно в 8:00 по будням) |

## 📁 Структура проекта
```
API_Rest_Assured_Allure_Jenkins/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── client/         # HTTP-клиенты
│   │   │   ├── config/         # Конфигурация API (ApiConfig)
│   │   │   └── models/         # POJO-классы (User, Address, Company, Geo)
│   │   └── resources/          # Ресурсы (JSON-схемы)
│   └── test/
│       └── java/
│           ├── assertions/     # Кастомные проверки (UserAssertions)
│           ├── data/           # DataProvider'ы (UserData)
│           ├── DeleteUserTests.java
│           ├── GetUserTests.java
│           ├── PostUserTests.java
│           └── UpdateUserTests.java
├── Jenkinsfile
├── Dockerfile
├── Dockerfile.jenkins
├── docker-compose.yml
├── pom.xml
└── README.md
```