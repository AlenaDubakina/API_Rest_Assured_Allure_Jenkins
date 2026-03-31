# 1. Базовый образ
FROM maven:3.9-eclipse-temurin-17

# 2. Рабочая директория внутри контейнера
WORKDIR /app

# 3. Копируем pom.xml для кэширования зависимостей
COPY pom.xml .

# 4. Загружаем зависимости (кэшируется, если pom.xml не менялся)
RUN mvn -B -q -e -DskipTests dependency:go-offline

# 5. Копируем исходный код
COPY src ./src

# 6. Команда по умолчанию
CMD ["mvn", "test"]