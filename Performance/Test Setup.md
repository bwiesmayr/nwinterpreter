# Main configuration of the test setup
* Test suite used for Section 6.1 cf. https://github.com/eclipse-4diac/4diac-examples/tree/master/compliance_tests 
* The class BenchmarkLoopTest.java can be executed as an Eclipse Plug-in test and reads a benchmark project (4diac IDE project with IEC 61499 example) - Section 6.2 Scalability test
* The class RecordExecutionHandler.java is part of Eclipse 4diac. It was instrumented to print the current time in ns on the console to estimate the execution time (Section 6.3 Application example).
