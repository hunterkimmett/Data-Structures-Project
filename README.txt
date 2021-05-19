README

This file contains information on how to use .java files for each exercise.
Files have been submitted in src.zip folder for convenience.
src folder contains folders for input and output for convenience and to ensure src folder stays organized.

---------
Exercise1
---------
Ensure file path leads to this src folder with:
cd filepath\src

Compile with following command:
javac Exercise1.java

Execute with the following command:
java Exercise1 order size algorithm output

Where:
order - can be one of the following: ascending, descending, random
size - can be any positive integer
algorithm - can be one of the following: bubble, insertion, merge, quick
output - can be any file type, however it is recommended to use output1 folder and is required to specify filetype (recommend .txt)

Example execution:
java Exercise1 random 1000 merge output1\output1.txt

Troubleshooting:
- If output file appears to be chinese characters, ensure encoding on text reader is set to UTF-8.
- Size of over 10,000 will make quick sort have stack overflow issues, 
  this can be solved by adding the following between java and Exercise1 in command line:

  -Xms2048k -Xmx14336m -Xss65536k

Example:
java -Xms2048k -Xmx14336m -Xss65536k Exercise1 ascending 100000 quick output1\output1.txt

---------
Exercise2
---------
Ensure file path leads to this src folder with:
cd filepath\src

Compile with following command:
javac Exercise2.java

Execute with the following command:
java Exercise2 input output

Where:
input - can be any text file, however files included are in input2 folder. Files included:
	8words_out.txt, 10000words.txt, 100000words.txt, 10anawords.txt, 10nowords.txt
output - can be any file type, however it is recommended to use output2 folder and is required to specify filetype (recommend .txt)

Example execution:
java Exercise2 input2\8words.txt output2\output2.txt

Troubleshooting:
- If output file appears to be chinese characters, ensure encoding on text reader is set to UTF-8.
- Size of over 10,000 will cause program to have stack overflow issues, this can be solved 
  by adding the following between java and Exercise2 in command line:

-Xms2048k -Xmx14336m -Xss65536k

Example:
java -Xms2048k -Xmx14336m -Xss65536k Exercise2 input2\100000words.txt output2\output2.txt