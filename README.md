
### Dependencies : 

* Lombok : compile-time (provided scope)
* Jackson for CSV (de)marschalling.
* Optional Guice for DI ?
* Interactive webserver ?


### Choices :
* Flatten the model. No hierachy.
* Why not use toString() ? I used a Formatter because it's a specific
feature of the app
* POJOs as separate classes. That allows flexibility and they should not 
be anything else than the pure reflection of the csv structures. NO LOGIC IN THEM.



### Possible enhancements
* ASYNC loading of CSVs with rxJava. --> DONE
* Data validation and error handling.

### Difficulties 
* CSV ',' values within csv ';' rows for author lists.
* Date deserialization (non-standard format)
