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
import com.sun.jna.Library;
import com.sun.jna.Native;


interface LibC extends Library,SyslogConstants {
	
	LibC instance = (LibC)Native.loadLibrary("c", LibC.class);
	void openlog(String ident, int option, int facility);
	void syslog(int facility_priority, String ... format);
	void closelog();
	void setlogmask(int mask);
}
