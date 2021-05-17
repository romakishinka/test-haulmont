FROM java:8
EXPOSE 8080
ADD /target/SoapShop.jar SoapShop.jar
ENTRYPOINT ["java", "-jar", "SoapShop.jar"]