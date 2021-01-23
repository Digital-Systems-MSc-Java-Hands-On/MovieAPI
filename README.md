# Introduction
Hands-on demo project to showcase how to consume a RESTful web service with Java to get information about movies and Dependency Management with Maven hands-on

# API
[TMDB API](https://developers.themoviedb.org/3/getting-started/introduction)

# Requirements
You'll need to go create an account to get an API key ([https://www.themoviedb.org/documentation/api](https://www.themoviedb.org/documentation/api))

# Dependencies

 - Jackson (for the JSON -> Java POJO Deserialization)
``` 
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-databind</artifactId>
	<version>2.12.1</version>
</dependency>
```
 - JUnit (for writing Unit Tests)
 ``` 
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.13.1</version>
	<scope>test</scope>
</dependency>
```
- Apache HttpClient (to consume the API)
 ``` 
<dependency>
	<groupId>org.apache.httpcomponents</groupId>
	<artifactId>httpclient</artifactId>
	<version>4.5.13</version>
</dependency>
```
# Maven Coordinates

To add this library as a dependency add the following maven coordinates into your pom.xml file

    <dependency>
	    <groupId>gr.unipi</groupId>
	    <artifactId>MovieAPI</artifactId>
	    <version>0.0.1-SNAPSHOT</version>
	</dependency>
