# CS167 Lab 2 Questions

### Question 1: Verify the file size and report the running time.

- The file is approximately 2.3 GB of data, and took approximately 6.4 seconds to copy over.


### Question 2: Report the running time of the copy command

- The file took a total of apprixamately 1.4 seconds to copy over using the Linux CP command.


### Questions 3: How do these numbers compare?

- Obviously using the Linux command was faster. This is because using the program we wrote, the file is copied over in very small chunks compared the the streamlining that the OS is capable of doing to improve efficiency.

### Question 4: What was the result?

- Exception in thread "main" java.lang.ClassNotFoundException: edu.ucr.cs.cs167.mjone032.App
  at java.net.URLClassLoader.findClass(URLClassLoader.java:387)
  at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
  at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
  at java.lang.Class.forName0(Native Method)
  at java.lang.Class.forName(Class.java:348)
  at org.apache.hadoop.util.RunJar.run(RunJar.java:316)
  at org.apache.hadoop.util.RunJar.main(RunJar.java:236)

### Question 5:

- i: hdfs dfs -put AREAWATER.csv  7.15s user 2.00s system 162% cpu 5.618 total
- ii: hdfs dfs -get AREAWATER.csv  4.72s user 2.49s system 119% cpu 6.035 total
- iii: hdfs dfs -cp AREAWATER.csv awCopy.csv  8.81s user 1.81s system 162% cpu 6.547 total


