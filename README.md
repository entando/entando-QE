# entando-postman/selenium

__Postman version__

Postman v.6.0.10 +

Collections v 2.1


### install newman package

__Prerequisites__

* Node and npm installed on your machine
* newman and postman-combine-collections installed as global in your environment

### install newman package

`npm install newman --global`

### install postman-combine-collections

`npm install postman-combine-collections --global`

We can choose to execute single collection tests from the `postman_API` directory
or execute all tests simultaneously by grouping all collections in a new
generated json document.

__execute single collection's tests__

`newman run postman_API/Access-Token.postman.collection.json -e postman_API/environment/Entando5.postman_environment.json`

__execute all collections's tests__

Group all collections by executing: `postman-combine-collections --filePath=postman_API/*.json`
then execute `newman run root.collection.json -e postman_API/environment/Entando5.postman_environment.json`
