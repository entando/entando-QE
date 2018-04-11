#!/bin/bash

## env variables
rootFile="root.collections.json"
collections="postman_API/Label*.json"

echo "{\"item\":[" > "$rootFile"

for collection in postman_API/*.json; do
    echo "," >> "$rootFile"
    cat "$collection" >> "$rootFile"
done

echo "]}" >> "$rootFile"