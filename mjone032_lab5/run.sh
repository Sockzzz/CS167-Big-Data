mvn clean package

spark-submit --class edu.ucr.cs.cs167.mjone032.Filter target/mjone032_lab5-1.0-SNAPSHOT.jar hdfs:///nasa_19950630.22-19950728.12.tsv hdfs:///filter_output 302
spark-submit --class edu.ucr.cs.cs167.mjone032.Aggregation target/mjone032_lab5-1.0-SNAPSHOT.jar hdfs:///nasa_19950630.22-19950728.12.tsv 2>/dev/null
