#!/bin/bash

## env variables
envDir="postman_API/environment"
envFile="Entando5API.postman_environment.json"

for collection in postman_API/*.json; do
<<<<<<< HEAD
    newman run "$collection" -e "$envDir"/"$envFile" --reporters cli,junit --reporter-junit-export newman.xml --suppress-exit-code
=======
    newman run $collection -e $envDir/$envFile --reporters cli,junit --reporter-junit-export newman.xml --suppress-exit-code
>>>>>>> 4d695257186264cf6a40f706e44c2d27b1f81828
    cat newman.xml >> newman_.xml
done

## Format the newly generated file
<<<<<<< HEAD
sed -i '0,/<?xml version="1.0" encoding="UTF-8"?>/! s/<?xml version="1.0" encoding="UTF-8"?>//g' ./newman_.xml
=======
sed -i '0,/<?xml version="1.0" encoding="UTF-8"?>/! s/<?xml version="1.0" encoding="UTF-8"?>//g' ./newman_.xml
>>>>>>> 4d695257186264cf6a40f706e44c2d27b1f81828
