#!/bin/bash

## env variables
envDir="postman_API/environment"
envFile="Entando5API.postman_environment.json"

newman run postman_API/Access-Token.postman_collection.json -e "$envDir"/Entando5API.postman_environment.json --export-environment Entando5API.postman_environment.json

for collection in postman_API/*.json; do
    newman run "$collection" -e "$envFile" --reporters cli,junit --reporter-junit-export newman.xml --suppress-exit-code
    cat newman.xml >> newman_.xml
done

## Format the newly generated file
sed -i '0,/<?xml version="1.0" encoding="UTF-8"?>/! s/<?xml version="1.0" encoding="UTF-8"?>//g' ./newman_.xml
