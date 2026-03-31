# 1. Базовый образ
FROM maven:3.9-eclipse-temurin-17

# 2. Рабочая директория внутри контейнера
WORKDIR /app

# 3. Копируем pom.xml для кэширования зависимостей
COPY pom.xml .

# 4. Загружаем зависимости (кэшируется, если pom.xml не менялся)
RUN mvn -B -q -e -DskipTests dependency:go-offline

# 5. СОЗДАЕМ ТАРГЕТ И ДАЕМ ПРАВА
# Это гарантирует, что любой пользователь внутри контейнера сможет писать отчеты
RUN mkdir -p /app/target/allure-results && chmod -R 777 /app/target

# 6. Копируем исходный код
COPY src ./src

# 6. Команда по умолчанию
CMD ["mvn", "test"]