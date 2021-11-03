FROM openjdk:12
VOLUME /tmp
##EXPOSE 8761  ##comentamos este por que el puerto sera ramdon asigando por eureka
ADD ./target/springboot-servicio-usuarios-0.0.1-SNAPSHOT.jar servicio-usuarios.jar
ENTRYPOINT ["java","-jar","/servicio-usuarios.jar"]