#**Shamir's Secret Sharing - Java Implementation**

This project implements a simplified version of Shamir's Secret Sharing algorithm in Java. The goal is to decode polynomial roots provided in various bases, compute the coefficients of the polynomial, and retrieve the secret constant term c.

##**File Structure**

/ShamirSecretSharing

    ├── src/
    │   └── ShamirSecretSharing.java  (Main Java file containing the implementation)
    
    ├── lib/
    │   └── json-20210307.jar        (External JSON library for parsing input)
    
    ├── test/
    │   └── input.json               (Test case input in JSON format)
    
    └── README.md                    (This file)


##**Description of Files**

- src/ShamirSecretSharing.java: Contains the main Java code for the Shamir Secret Sharing algorithm implementation.
- lib/json-20210307.jar: External library for handling JSON parsing. This JAR is required for reading input data.
- test/input.json: A test case file that contains the polynomial roots encoded in various bases. This is used as input to the program.
- README.md: Documentation for the project, providing instructions on setup and execution.
