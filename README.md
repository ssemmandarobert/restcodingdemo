# restcodingdemo
This a maven application and will need to be compiled and built with maven. 
As we are utilising vaadin charts, to compile this, we will need to specify the demo key provided along with the maven build /compile commands. 
For this demo, the key is 5132921c-315e-4c7d-bf20-531d41ce54ec and will expire within two weeks time from this submission. 
-Dvaadin.charts.developer.license=5132921c-315e-4c7d-bf20-531d41ce54ec package

Should you need to acquire another developer license, you can signup at vaadin and request for one or purchase one at https://vaadin.com/charts  

I have implemented a rudementary rate limiting implementation in the class CurrencyRestController in the method isAllowedRequest. 
As of this commmit, the API is limited to 5000 requests per minute. This is at the method level. 

Because this application is built using spring-boot, it can be built as a war or executable jar. The current commit builds a war file. 

The UI can be accessed at http://localhost:8080/ 
The REST API can be accessed at http://localhost:8080/api/1.0/  

