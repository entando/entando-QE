#!/bin/bash

## env variables
envDir="postman_API/environment"
envFile="Entando5API.postman_environment.json"

for collection in postman_API/*.json; do
    newman run $collection -e $envDir/$envFile --reporters cli,junit --reporter-junit-export newman.xml --suppress-exit-code
done
