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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.StringTokenizer;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class LocalSyslogAppender extends AppenderBase<ILoggingEvent> {
	
	private String _ident=null;
	private String _option=null;
	private String _facility=null;
	
	@Override
	public void start() {
		
		boolean hasError = false;
		int option=0;
		if (_option == null) {
			addError("syslog option not defined");
			hasError = true;
		} else {
			try {
				option = convertOptionExpToInt(_option);
			} catch (Exception e) {
				addError("Option=["+_option+"]", e);
				hasError = true;
			}
		}
		
		int facility=0;
		if (_facility!=null) {
			try {
				facility = convertFacilityExpToInt(_facility);
			} catch (Exception e) {
				addError("Facility=["+_facility+"]", e);
				hasError = true;
			}
		}
		
		if (!hasError)
		{
			LibC.instance.openlog(_ident, option, facility);
			super.start();
		}
	}
	
	@Override
	public void stop() {
		LibC.instance.closelog();
		super.stop();
	}
	
	private int getConstantValueByFieldName(String fieldName) throws IllegalArgumentException, IllegalAccessException {
		Field[] declaredFields = SyslogConstants.class.getDeclaredFields();
		for (int i=0; i<declaredFields.length; i++) {
			Field field = declaredFields[i];
			int modifier = field.getModifiers();
			if (Modifier.isStatic(modifier) && Modifier.isFinal(modifier))
				if (field.getName().equals(fieldName))
					return field.getInt(null);
		}
		throw new IllegalArgumentException("Unknown field "+fieldName);
	}
	
	private int convertFacilityExpToInt(String facility) throws IllegalArgumentException, IllegalAccessException {
		return getConstantValueByFieldName(facility);
	}

	private int convertOptionExpToInt(String option) throws IllegalArgumentException, IllegalAccessException {
		StringTokenizer tokenizer = new StringTokenizer(option,"|");
		int result = 0;
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();
			result |= getConstantValueByFieldName(token);
		}
		return result;
	}

	@Override
	protected void finalize() throws Throwable {
		LibC.instance.closelog();
		super.finalize();
	}
	
	public String getIdent() {
		return _ident;
	}

	public void setIdent(String ident) {
		this._ident = ident;
	}

	public String getOption() {
		return _option;
	}

	public void setOption(String option) {
		this._option = option;
	}

	public String getFacility() {
		return _facility;
	}

	public void setFacility(String facility) {
		this._facility = facility;
	}

	@Override
	protected void append(ILoggingEvent ile) {
		if (!isStarted()) return;
		Level level = ile.getLevel();
		LibC.instance.syslog(convertLevelToInt(level), ile.getFormattedMessage());
	}
	
	private int convertLevelToInt(Level level) {
		switch (level.levelInt) {
		case Level.ERROR_INT:
			return LibC.LOG_ERR;
		case Level.WARN_INT:
			return LibC.LOG_WARNING;
		case Level.INFO_INT:
			return LibC.LOG_INFO;
		case Level.DEBUG_INT:
		case Level.TRACE_INT:
		default:
			return LibC.LOG_DEBUG;
		}
	}
}
