Please run ApplicationMarsRoversTest from IDE and this class will test output with given input from INPUT.txt

You can also run "mvn clean test" which will run all the tests.

 Please note that I have demonstrated how requirements could be extended, by adding additional compass directions.
 After adding new instruction A,B to Instruction class we now can also move by 45 degrees and currently NorthWestMoveStrategy
 is supported, but all the rest could be added easily, this is just done to demonstrate how this could be extended.

 For the sake of being quick I have skipped couple things like asynchronous processing of rover deployments,
 which could be easily added by usage of ExecutorService and Future. And I didn't also pay attention to performance, just
 so you know.

 Code has been tested with jdk1.8.0.jdk.

 Thank you for your time.




