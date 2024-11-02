FROM openjdk:21
COPY module-api/build/libs/module-api.jar ./module-api.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "./module-api.jar"]