#!/bin/bash
set -e

PID=$(pgrep -f "app.jar" || true)
[ -n "$PID" ] && kill -9 $PID

rm -f app.jar
mv /home/ubuntu/appstore-service.jar app.jar

nohup java -jar app.jar --server.port=8085 > app.log 2>&1 &

