FROM java:8
VOLUME /tmp
ADD target/eureka-server-0.0.1-SNAPSHOT.jar /app.jar
RUN bash -c 'touch /app.jar'
RUN echo "Asia/shanghai" > /etc/timezone
EXPOSE 8080
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-Djava.security.egd=file:/./urandom","-jar","/app.jar","--spring.profiles.active=prod"]