# spring-cloud-microservice
Simple implementation of Microservices Using Spring Cloud.

1. In the parent folder: spring-cloud-microservice do: mvn clean install

#Perform Steps 2-6 in parallel of individual command prompts

2. Launch config-microservice
	1. mvn spring-boot:run
	
3. Launch discovery-microservice
	1. mvn spring-boot:run 

4. Launch api-gateway-microservice
	1. mvn spring-boot:run

5. Launch product-catalog-microservice
	1. mvn spring-boot:run
	2. mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=8900'
	
6. Launch price-microservice
	1. mvn spring-boot:run

7. Load : http://localhost:8761/
	1. you should be able to see:
