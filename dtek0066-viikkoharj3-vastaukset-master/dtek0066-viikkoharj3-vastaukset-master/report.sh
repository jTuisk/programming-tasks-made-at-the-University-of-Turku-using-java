#!/bin/sh
find src > public/report.txt
mvn clean test 2>&1 >> public/report.txt
mvn surefire-report:report
cp target/site/surefire-report.html public/
