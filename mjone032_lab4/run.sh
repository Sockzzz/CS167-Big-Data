#!/usr/bin/env sh
mvn clean package
hadoop jar target/mjone032_lab4-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.mjone032.Filter file:////Users/marshalljones/cs167/workspace/mjone032_lab4/nasa_19950801.tsv file:////Users/marshalljones/cs167/workspace/mjone032_lab4/filter_output.tsv 200
hadoop jar target/mjone032_lab4-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.mjone032.Aggregation file:////Users/marshalljones/cs167/workspace/mjone032_lab4/nasa_19950801.tsv file:////Users/marshalljones/cs167/workspace/mjone032_lab4/aggregation_output.tsv