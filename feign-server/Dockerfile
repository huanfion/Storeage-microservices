FROM java:8
VOLUME /tmp
ADD target/feign-server-0.0.1-SNAPSHOT.jar /feign-server-app.jar
RUN bash -c 'touch /feign-server-app.jar'
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/shanghai" > /etc/timezone
EXPOSE 8885
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-Djava.security.egd=file:/./urandom","-jar","/feign-server-app.jar","--spring.profiles.active=prod"]