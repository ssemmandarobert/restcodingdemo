# restcodingdemo
This a maven application and will need to be compiled and built with maven. 
As we are utilising vaadin charts, to compile this, we will need to specify the demo key provided along with the maven build /compile commands. For this demo, the key is 5132921c-315e-4c7d-bf20-531d41ce54ec and will expire within two weeks time from this submission. 
-Dvaadin.charts.developer.license=5132921c-315e-4c7d-bf20-531d41ce54ec package

Should you need to acquire another developer license, you can signup at vaadin and request for one or purchase one at https://vaadin.com/charts  

Because this application is built using spring-boot, it can be built as a war or executable jar. The current commit builds a war file. 

This is a sample application I wrote for an interview. It uses Springboot, vaadin, springdata, maven, REST and an embedded H2 database. You can build it as an executable jar file thanks to springboot or you can build a war file that will be deployed to a server.  
The UI can be accessed at http://localhost:8080/ 
The REST API can be accessed at http://localhost:8080/api/1.0/  
As this application utilizes vaadin charts, it is up to you to get your own sample/trial license to be able to compile with vaadin charts. You can get at : https://vaadin.com/charts  
When compiling or building the application, you must supply your demo / purchased key. With maven you will have to include it as a parameter with the mvn command i.e mvn -Dvaadin.charts.developer.license=XXXXX-XXXXXXXXXX build
