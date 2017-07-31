# restcodingdemo
This is a sample application I wrote for an interview. It uses Springboot, vaadin, springdata, maven, REST and an embedded H2 database. You can build it as an executable jar file thanks to springboot or you can build a war file that will be deployed to a server.  
The UI can be accessed at http://localhost:8080/ 
The REST API can be accessed at http://localhost:8080/api/1.0/  
As this application utilizes vaadin charts, it is up to you to get your own sample/trial license to be able to compile with vaadin charts. You can get at : https://vaadin.com/charts  
When compiling or building the application, you must supply your demo / purchased key. With maven you will have to include it as a parameter with the mvn command i.e mvn -Dvaadin.charts.developer.license=XXXXX-XXXXXXXXXX build
