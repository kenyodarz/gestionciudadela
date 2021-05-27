FROM openjdk
RUN mkdir app
ADD target/gestionciudadela-1.0.jar app/gestionciudadela-1.0.jar 
WORKDIR app
RUN "pwd"
RUN "ls"
EXPOSE 8080
ENTRYPOINT ["java","-jar", "gestionciudadela-1.0.jar "]