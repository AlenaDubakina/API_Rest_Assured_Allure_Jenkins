# API Testing Project
Проект для автоматизации тестирования REST API с использованием современного стека технологий.

## 📝 Описание
Тестируется публичное API [JSONPlaceholder](https://jsonplaceholder.typicode.com)

**Ключевые возможности:**
- Полный CRUD цикл для ресурса `/users` (GET, POST, PUT, PATCH, DELETE)
- Параметризованные тесты с `DataProvider`
- Fluent assertions с AssertJ
- POJO модели с Lombok и `@Accessors(chain = true)`
- Гибкое логирование через `.log().ifValidationFails()`
- Генерация Allure отчётов

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

## 🔄 CI/CD

Проект интегрирован с Jenkins для автоматического запуска тестов.

| Инструмент | Назначение |
|------------|------------|
| **Jenkins** | CI/CD сервер |
| **GitHub** | Хранение кода и Jenkinsfile |
| **Allure Jenkins Plugin** | Публикация отчётов в Jenkins |
| **Cron (H 8 * * 1-5)** | Расписание запуска (ежедневно в 8:00 по будням) |

## 📁 Структура проекта

API_Rest_Assured_Allure_Jenkins/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── client/         # HTTP-клиенты
│   │   │   ├── config/         # Конфигурация API (ApiConfig)
│   │   │   └── models/         # POJO-классы (User, Address, Company, Geo)
│   │   └── resources/          # Ресурсы (JSON-схемы и т.д.)
│   └── test/
│       ├── java/
│           ├── assertions/      # Кастомные проверки (UserAssertions)
│           ├── data/            # DataProvider'ы (UserData)
│           ├── DeleteUserTests.java
│           ├── GetUserTests.java
│           ├── PostUserTests.java
│           └── UpdateUserTests.java               
├── Jenkinsfile
├── pom.xml
└── README.md