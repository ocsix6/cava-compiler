# CAVA Compiler

## Explanation
This project presents the development of a compiler made from scratch. It has been carried out during the Compilers course by the team formed by [JSf98](https://github.com/JSf98), [JCC1998](https://github.com/JCC1998) and [me](https://github.com/ocsix6).

## To compile:
1. Generate the lexical file using JFlex:
    * open lib/jflex-full.jar
    * select the .flex file and generate the output (Scanner.java)

2. Generate the syntactic files using CUP:
    ```
    # execute just one option
    java -jar java-cup-11b.jar -parser parser Parser.cup # option 1
    java -jar java-cup-11b.jar Parser.cup # option 2
    ```
    It will generate Parser.java and ParserSym.java files.

3. Add the generated files to the CAVA project (CAVA/src/cava).

4. Execute java project. The program reads the input under the file input.txt to generate the corresponding machine code (if no errors detected).

5. Execute makeExec.sh or makeExecOpt.sh (optimized version of the code) to compile and run the machine code generated.


## Dependencies
<!--- All dependencies are inside the "dependencies" compressed file, specifically: --->
- JFLex 1.7
- Java CUP 11b