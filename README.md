# foursquare_api
##Description
**foursquare_api** is a Spring boot application based on web. It retrieves POIs from Foursquare API and displays them on a map.

##Technogy Stack
###Backend
- Spring Boot
- foursquare-api (version 1.0.3-SNAPSHOT)
- JUnit and Mockito (for testing)

###Frontend
- AngularJs
- Bootstrap
- HTML

## How to compile the application
1. Add third party foursquare-api to the project (can't download last version from remote repository directly.
We need last version because of compatibility issues)
    1. Clone https://github.com/clinejj/foursquare-api-java.git into a local folder
    2. Execute **mvn clean install** from the foursquare-api-java root folder (this will update in  your local repository)

2. Run **mvn clean install** from your root folder

## How to run the application
1. Run **mvn spring-boot:run**
2. Go to your favourite browser and type **http://localhost:8080/index.html**

