#!/bin/sh
exec java -jar -Dspring.profiles.active=docker /app/app.jar