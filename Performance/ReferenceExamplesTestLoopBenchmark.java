/*******************************************************************************
 * Copyright (c) 2023 Johannes Kepler University Linz
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Bianca Wiesmayr, Antonio Garmendia - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.test.fb.interpreter.fbnetwork;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.fordiac.ide.fb.interpreter.OpSem.EventManager;
import org.eclipse.fordiac.ide.fb.interpreter.mm.FBNetworkTestRunner;
import org.eclipse.fordiac.ide.model.libraryElement.Application;
import org.eclipse.fordiac.ide.model.libraryElement.AutomationSystem;
import org.eclipse.fordiac.ide.model.libraryElement.Event;
import org.eclipse.fordiac.ide.model.libraryElement.FB;
import org.eclipse.fordiac.ide.model.libraryElement.SubApp;
import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ReferenceExamplesTestLoopBenchmark extends ReferenceExamplesTest {

	private static final String[] CSV_MEASUREMENT_HEADERS = { "Execution Number", "FB Name", "LoopTo", "SubApp Name", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"Start Time (nanosec)", "End Time (nanosec)", "Execution Time (EndTime - StartTime)" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	private static final int NUMBER_REPETITIONS = 25;

	private static final int[] loopRuns = { 10, 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };

	public static IResource[] getTraceNamesBenchmark() throws CoreException {
		System.out.println("load project"); //$NON-NLS-1$
		return loadProject("network_traces", "BenchmarkProject"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@ParameterizedTest
	@MethodSource("getTraceNamesBenchmark")
	@Override
	public void runTest(final IResource res) {
		// print what test it is
		System.out.println("TEST CASE " + res.getName()); //$NON-NLS-1$
		System.out.println("*****************************"); //$NON-NLS-1$
		System.out.println();

		// load test
		final String[] request = res.getName().split("\\."); //$NON-NLS-1$
		// first element: system
		final AutomationSystem system = getLoader().getAutomationSystem(request[0]);
		// second element: application
		final Application app = system.getApplicationNamed(request[1]);
		// third element: subapplication name
		final SubApp testCase = (SubApp) app.getFBNetwork().getElementNamed(request[2]);
		// fourth element: FB to trigger
		final FB initialFb = (FB) testCase.getSubAppNetwork().getElementNamed(request[3]);
		// fifth element: pin to trigger
		final Event eventPin = (Event) initialFb.getInterface().getInterfaceElement(Arrays.asList(request[4]));
		// sixth element must be opsem
		assertEquals(request[5].equals("opsem"), true); // $NON-NLS-1$

		final FB counterFB = testCase.getSubAppNetwork().getFBNamed("counter"); //$NON-NLS-1$
		final VarDeclaration varDecl = (VarDeclaration) counterFB.getInterface()
				.getInterfaceElement(Arrays.asList("PV")); //$NON-NLS-1$

		// Create CSV File
		try {
			final File dir = new File("workbench-measurement"); //$NON-NLS-1$
			if (!dir.exists()) {
				dir.mkdirs(); // creates parent directories if needed
			}
			final FileWriter fileWriter = new FileWriter("workbench-measurement/" + res.getName() + ".csv"); //$NON-NLS-1$ //$NON-NLS-2$
			final CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(CSV_MEASUREMENT_HEADERS).build();
			try (final CSVPrinter printer = new CSVPrinter(fileWriter, csvFormat)) {
				for (final int loopRun : loopRuns) {
					varDecl.getValue().setValue(String.valueOf(loopRun));
					System.out.println("LOOP RUN " + loopRun); //$NON-NLS-1$
					System.out.println("*****************************"); //$NON-NLS-1$
					System.out.println();
					for (int i = 0; i < NUMBER_REPETITIONS; i++) {
						System.out.println("try " + i); //$NON-NLS-1$
						// Start Measurement
						final long startTime = System.nanoTime();
						// create and execute event manager
						final EventManager eventManager = FBNetworkTestRunner
								.runFBNetworkTestManager(testCase.getSubAppNetwork(), eventPin);
						// End Measurement
						final long endTime = System.nanoTime();
						try {
							printer.printRecord(Integer.valueOf(i + 1), initialFb.getName(), Integer.valueOf(loopRun),
									testCase.getName(), Long.valueOf(startTime), Long.valueOf(endTime),
									Long.valueOf(endTime - startTime));
						} catch (final IOException e) {
							e.printStackTrace();
						}
					}
				}
				// Save File
				fileWriter.flush();
				fileWriter.close();
				printer.close();
			} catch (final IOException e1) {
				e1.printStackTrace();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
