FROM openjdk:21
COPY module-scheduler/build/libs/module-scheduler.jar ./module-scheduler.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "./module-scheduler.jar"]