#!/usr/bin/env sh
time hdfs dfs -put AREAWATER.csv
hdfs dfs -mv AREAWATER.csv AREAWATER_hdfs.csv
time hdfs dfs -get AREAWATER_hdfs.csv
time hdfs dfs -cp AREAWATER_hdfs.csv awCopy.csv