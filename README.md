# Additional materials to "Operational Semantics for IEC 61499 FB Networks"

This repository contains the following elements:
* The code added for measuring the execution time of the interpreter [(in a folder)](https://github.com/bwiesmayr/nwinterpreter/tree/main/Performance)
* The [measurement results](https://github.com/bwiesmayr/nwinterpreter/tree/main/Performance) obtained from the scalability test and the analysis script
* A package with the full code for potential replication of the measurement results.


## Instructions For Replicating the Performance Measurement
* Download Eclipse [IDE Committer Edition 2025-09](https://www.eclipse.org/downloads/packages/release/2025-09/r/eclipse-ide-rcp-and-rap-developers) for your platform
* Clone the repository of [4diac-ide](https://github.com/eclipse-4diac/4diac-ide). The performance measurement was conducted with the [develop version available on 16.12.2025](https://github.com/eclipse-4diac/4diac-ide/tree/b65f73a7c197e813e359b8d8935c7448ae014063). 
* Replace the folder tests/org.eclipse.fordiac.ide.test.fb.interpreter with the one provided in this repository as [a zip file](https://github.com/bwiesmayr/nwinterpreter/blob/main/Performance/org.eclipse.fordiac.ide.test.fb.interpreter.zip)
* Import all projects from Git into Eclipse IDE (File/Import/Projects from Git)
* Within Eclipse IDE, locate the project org.eclipse.fordiac.ide.product and the file org.eclipse.fordiac.ide.product.target and add the following Maven dependency:
 ``` 
  		<dependency>
					<groupId>org.apache.commons</groupId>
					<artifactId>commons-csv</artifactId>
					<version>1.14.1</version>
					<type>jar</type>
				</dependency>
 ``` 
* Set the target platform it as active target platform (top right in the editor). Make sure that the target platform is loading (progress bar at the bottom right), if needed, select "Reload target platform".
* Locate the file org.eclipse.fordiac.ide.product in the same folder. You can test that the project was built correctly in the section "Testing". First, select Synchronize and then (optional!) "Launch an Eclipse application". This starts 4diac IDE.
* To run the performance test, open the project org.eclipse.fordiac.ide.test.fb.interpreter (the modified version). In the folder src, package org.eclipse.fordiac.ide.test.fb.interpreter.fbnetwork, locate the class ReferenceExamplesTestLoopBenchmark.java
* Right-click on the class and select Run as / JUnit Plug-in Test
  <img width="669" height="308" alt="image" src="https://github.com/user-attachments/assets/562c012b-af55-4ad7-ba84-03bd3b12f840" />
* The resulting CSV files will be stored in the folder workbench-measurement, which will be created within the project.

For further instructions on how to build the 4diac-ide repository, please see the project documentation at https://github.com/eclipse-4diac/4diac-documentation/blob/main/src/development/building4diac.adoc

## Instructions For Using the Tool Environment
To avoid any issues, some features are currently deactivated in the production version of Eclipse 4diac. For comparing traces with EMF compare, override the default comparison mechanism of Eclipse 4diac:
    * Locate the file plugin.xml in the project org.eclipse.fordiac.ide.fb.interpreter and open the textual editor. 
    * Locate the lines adding the EMF compare attachment and increase the ranking (default is 150):
 ``` 
          <engineFactory
            class="org.eclipse.fordiac.ide.fb.interpreter.compare.FordiacForteInterpreterMatchEngine"
            ranking="1500">
      </engineFactory>
 ``` 
