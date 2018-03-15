How to run
From project folder do:

mvn install
java -jar target/lottery-1.0-SNAPSHOT.jar 30/09/2014  "1,2,3,4,5,6"

===

Some notes:
Rules could be loaded from database  as a set per lottery type
They can also be easily added and there might be as many rules configured per game as required
The only thing required is to create a new class per rule and set up it's priority and logic
The approach frees us from adding if/else statements and enables dynamic rules

     