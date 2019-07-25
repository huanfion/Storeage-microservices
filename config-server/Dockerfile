FROM java:8
VOLUME /tmp
ADD target/config-server-0.0.1-SNAPSHOT.jar /config-server-app.jar
RUN bash -c 'touch /config-server-app.jar'
RUN echo "Asia/shanghai" > /etc/timezone
EXPOSE 8888
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-Djava.security.egd=file:/./urandom","-jar","/config-server-app.jar","--spring.profiles.active=prod"]