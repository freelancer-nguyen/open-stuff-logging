/*******************************************************************************
 * Copyright (c) 2013 freelancer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors:
 *     freelancer - initial API and implementation
 ******************************************************************************/
package com.google.nguyen.freelancer.logging;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiThreadLoggingTest {
	
	private static Logger logger = LoggerFactory.getLogger(MultiThreadLoggingTest.class);

	private static final int numLog = 100;
	private static final int numThread = 4;
	
	@Test
	public void test() throws InterruptedException {
		List<Thread> threads = new ArrayList<Thread>(numThread);
		// Create
		for (int i=0; i<numThread; i++) {
			threads.add(new LogProducer());
		}
		// Launch
		for (int i=0; i<numThread; i++) {
			threads.get(i).start();
		}
		// Join
		for (int i=0; i<numThread; i++) {
			threads.get(i).join();
		}
	}

	private class LogProducer extends Thread {
		@Override
		public void run() {
			
			String name = getName();
			for (int i=0; i<numLog; i++){
				logger.info("{} writes {}",name, i);
			}
		}
	}
	
}
