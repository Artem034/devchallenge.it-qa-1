FROM maven:3.6.1-jdk-8-slim
COPY . .
CMD mvn clean test -Dselenide.remote=http://localhost:4444/wd/hub & libs\allure-2.7.0\bin\allure.bat serve target/allure-results -p 9999
EXPOSE 9999