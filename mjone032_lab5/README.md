# Lab 5

## Student information

* Full name: Marshall Jones
* E-mail: mjone032@ucr.edu
* UCR NetID: mjone032
* Student ID: 862062375

## Answers

* (Q1) Do you think it will use your local cluster? Why or why not?

    - No, the spark context is set to local and not any node.

* (Q2) Does the application use the cluster that you started? How did you find out?

    - Using Spark master 'local[*]'

* (Q3) What is the Spark master printed on the standard output on IntelliJ IDEA?

    - Using Spark master 'local[*]'

* (Q4) What is the Spark master printed on the standard output on the terminal?

  - local

* (Q5) For the previous command that prints the number of matching lines, list all the processed input splits.

  - Four counts of +1169610.

* (Q6) For the previous command that counts the lines and prints the output, how many splits were generated?

  - Six counts of +1169610.

* (Q7) Compare this number to the one you got earlier.

  - The second version has an additonal 2 input splits.

* (Q8) Explain why we get these numbers.
  
  - We have additional actions that take place in main, thus generating more splits.

* (Q9) What can you do to the current code to ensure that the file is read only once?

  - We can add matchingLines.cache() so that the information is retained in memory. (See Filter.java)