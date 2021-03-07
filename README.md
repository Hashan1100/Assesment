## Prerequisite

* Java openjdk version 11
* MySql 5.7.24 or Higher
* NodeJs v10.16.3 or Higher

## How to build

### Create the database

* Go to ```scripts```
* Update username and password for DB in ```initializeDb.sh```
* Run initializeDb.sh ```sh initializeDb.sh```

### shopping API

```
mvn clean install -DskipTests
```

### Shopping UI

* Go to ```shopping-ui-2```
* Run ```yarn install```

* Build will be available in ```shopping-api/target/shopping-api```

## How to run

### Run shopping API

* Build the application
* Go to ```shopping-api/target/shopping-api/bin```
* Run ```./shopping-api console```

### Run shopping ui

* Go to ```shopping-ui-2```
* Install node modules
* Run ```yarn start``` 
