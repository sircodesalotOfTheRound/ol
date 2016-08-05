###How to build:

````
mvn clean install  # Do a quick maven install.
````

###How to run:

````
$ chmod u+x run    # Make the run command executable
$ ./run            # Run the command
````

You should see something like this:
````
....
Aug 04, 2016 8:13:10 PM org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer start
INFO: Tomcat started on port(s): 8080 (http)
Aug 04, 2016 8:13:10 PM org.springframework.boot.StartupInfoLogger logStarted
INFO: Started Program in 2.693 seconds (JVM running for 4.333)
````

This means that the rest server is started on port 8080. You should be able to view it by navigating to:

````
# In your browser or curl or whatever
http://localhost:8080/businesses                        # This will list the first 50 businesses
{
  "pageNumber":0,
  "totalEntries":50000,
  "itemsOnPage":50,
  "businesses":[ 
    "id":0,"uuid":"2859d6e0-1cb9-4fe9-bc00-97823a9fa4cb",
    ...
  ]
}

http://localhost:8080/businesses/1                      # This will show the business at index 5.

{ "id":1,
  "uuid":"d083169c-4340-4a07-b390-07d297823efd",
  "name":"Botsford Ltd",
  "address":"74883 Hane Prairie",
  "address2":"",
  "city":"Margrettburgh",
  "state":"KS",
  "country":"99840",
  "phone":"US",
  "website":"2462288476",
  "createdAt":"http://bergstrom.org/"
}

http://localhost:8080/businesses/?page=3&pagesize=20    # This will show page '3' with a page size of 20.

{
  "pageNumber":3,
  "totalEntries":50000,
  "itemsOnPage":20,
  "businesses":[ ... ] 
}

````

