# CS167 Lab 4

## Student information

* Full name: Marshall Jones
* E-mail: mjone032@ucr.edu
* UCR NetID: mjone032
* Student ID: 862062375

## Answers

* (Q1) What do you think the line `job.setJarByClass(Filter.class);` does?
    - Set the Jar by finding where a given class came from.

* (Q2) What is the effect of the line `job.setNumReduceTasks(0);`?
    - It forces no reducers to execute.

* (Q3) Where does the `main` function run? (Driver node, Master node, or an executor node).
    - Master node

* (Q4) How many lines do you see in the output?
    - 27,972

* (Q5) How many files are produced in the output?
    - For the first tsv: 2 (not counting the crc files)
    - For the second tsv: 6

* (Q6) Explain this number based on the input file size and default block size.
    - The second input file requires much more space and therefore is split up more given the block size.

* (Q7) How many files are produced in the output?
  **Had to use this command as the one in the lab did not work**: hadoop jar target/mjone032_lab4-1.0-SNAPSHOT.jar file:///Users/marshalljones/cs167/first_workspace/mjone032_lab4/nasa_19950801.tsv filter_output.tsv 200
    - The **edu.ucr.cs.cs167.< UCRNetID >.Filter** in the lab manual was considered as an argument and would fail the program.
    - For the two nasa files, only two files are created, success and part-m-00000

* (Q8) Explain this number based on the input file size and default block size.
    - Its based on the default block size of 128MB. It fills the blocks up with as much of the data as possible before moving on and creating another one. We get one part file in here for each block needed, based on the input file size.

* (Q9) How many files are produced in the output directory and how many lines are there in each file?
    - 3 files total, success + two part files
    - part1 has 4 lines in it, part 1 is empty

* (Q10) Explain these numbers based on the number of reducers and number of response codes in the input file.
    - Since we have the number of reduce tasks set to 2 in the aggregate function, we whave two output files, even if they're not full. With 4 response codes, we get 4 lines.

* (Q11) How many files are produced in the output directory and how many lines are there in each file?
    - Two files were generated; the first has 5 lines, the second has two lines.

* (Q12) Explain these numbers based on the number of reducers and number of response codes in the input file.
    - Since the number of reduce tasks is set to two, we get two output files. However here we have 7 response codes, we get 7 lines of output.

* (Q13) How many files are produced in the output directory and how many lines are there in each file?
    - They're are two output files, the first has 1 line the second has none.

* (Q14) Explain these numbers based on the number of reducers and number of response codes in the input file.
    - Again since the number of reduce calls is set to two, we get two output files. Since we call this on the filter output, we only have a response code of 200 to adrress. Since there is only one code, we only get one line of output.