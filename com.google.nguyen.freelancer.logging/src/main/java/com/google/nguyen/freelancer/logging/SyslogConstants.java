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

public interface SyslogConstants {
	// Following constants are extracted from syslog.h

	static final int	LOG_EMERG	=0;	/* system is unusable */
	static final int	LOG_ALERT	=1;	/* action must be taken immediately */
	static final int	LOG_CRIT	=2;	/* critical conditions */
	static final int	LOG_ERR		=3;	/* error conditions */
	static final int	LOG_WARNING	=4;	/* warning conditions */
	static final int	LOG_NOTICE	=5;	/* normal but significant condition */
	static final int	LOG_INFO	=6;	/* informational */
	static final int	LOG_DEBUG	=7;	/* debug-level messages */
	
	/* facility codes */
	static final int	LOG_KERN	=(0<<3);	/* kernel messages */
	static final int	LOG_USER	=(1<<3);	/* random user-level messages */
	static final int	LOG_MAIL	=(2<<3);	/* mail system */
	static final int	LOG_DAEMON	=(3<<3);	/* system daemons */
	static final int	LOG_AUTH	=(4<<3);	/* security/authorization messages */
	static final int	LOG_SYSLOG	=(5<<3);	/* messages generated internally by syslogd */
	static final int	LOG_LPR		=(6<<3);	/* line printer subsystem */
	static final int	LOG_NEWS	=(7<<3);	/* network news subsystem */
	static final int	LOG_UUCP	=(8<<3);	/* UUCP subsystem */
	static final int	LOG_CRON	=(9<<3);	/* clock daemon */
	static final int	LOG_AUTHPRIV=(10<<3);	/* security/authorization messages (private) */

		/* other codes through 15 reserved for system use */
	static final int	LOG_LOCAL0	=(16<<3);	/* reserved for local use */
	static final int	LOG_LOCAL1	=(17<<3);	/* reserved for local use */
	static final int	LOG_LOCAL2	=(18<<3);	/* reserved for local use */
	static final int	LOG_LOCAL3	=(19<<3);	/* reserved for local use */
	static final int	LOG_LOCAL4	=(20<<3);	/* reserved for local use */
	static final int	LOG_LOCAL5	=(21<<3);	/* reserved for local use */
	static final int	LOG_LOCAL6	=(22<<3);	/* reserved for local use */
	static final int	LOG_LOCAL7	=(23<<3);	/* reserved for local use */
	
	/*
	 * Option flags for openlog.
	 *
	 * LOG_ODELAY no longer does anything.
	 * LOG_NDELAY is the inverse of what it used to be.
	 */
	static final int	LOG_PID		=0x01;	/* log the pid with each message */
	static final int	LOG_CONS	=0x02;	/* log on the console if errors in sending */
	static final int	LOG_ODELAY	=0x04;	/* delay open until first syslog() (default) */
	static final int	LOG_NDELAY	=0x08;	/* don't delay open */
	static final int	LOG_NOWAIT	=0x10;	/* don't wait for console forks: DEPRECATED */
	static final int	LOG_PERROR	=0x20;	/* log to stderr as well */
}
