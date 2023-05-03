#!/usr/bin/env sh


mvn package

spark-submit --class edu.ucr.cs.cs167.mjone032.project_task3 --master "local[*]" target/mjone032_lab6-1.0-SNAPSHOT.jar 03/01/2000 03/25/2018



