#!/bin/bash

DEFAULT_PORT=8081
DEFAULT_PROFILE=dev

PORT=${1:-$DEFAULT_PORT}
PROFILE=${2:-$DEFAULT_PROFILE}
RESOURCES_DIR="src/main/resources"
APPLICATION_PROPERTIES="$RESOURCES_DIR/application-${PROFILE}.properties"

if [ ! -f "$APPLICATION_PROPERTIES" ]; then
  touch "$APPLICATION_PROPERTIES"
fi

#cat .env > "$APPLICATION_PROPERTIES"

nohup mvn spring-boot:run -Dspring-boot.run.profiles=$PROFILE -Dspring-boot.run.arguments=--server.port=$PORT > /dev/null 2>&1 &