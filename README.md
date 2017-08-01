# restcodingdemo
This a maven application and will need to be compiled and built with maven. 
As we are utilising vaadin charts, to compile this, we will need to specify the demo key provided along with the maven build /compile commands. 
For this demo, the key is 5132921c-315e-4c7d-bf20-531d41ce54ec and will expire within two weeks time from this submission. 

mvn -Dvaadin.charts.developer.license=5132921c-315e-4c7d-bf20-531d41ce54ec package

Should you need to acquire another developer license, you can signup at vaadin and request for one or purchase one at https://vaadin.com/charts  

I have implemented a rudementary rate limiting implementation in the class CurrencyRestController in the method isAllowedRequest. 
As of this commmit, the API is limited to 5000 requests per minute. This is at the method level. 

The application uses websocket through the vaadin framework to update the UI when an event is received. 

Because this application is built using spring-boot, it can be built as a war or executable jar. The current commit builds a war file.
To build this as a standalone application, in the pom file change the packaging to jar and on the dependency with artifactid spring-boot-starter-tomcat, for groupid org.springframework.boot, comment out the scope <scope>provided</scope>.compile and build and you will have a runnable standalone jar. 

The UI can be accessed at http://localhost:8080/ 
The REST API can be accessed at http://localhost:8080/api/1.0/  

