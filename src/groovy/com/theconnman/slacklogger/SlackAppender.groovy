package com.theconnman.slacklogger;

import org.apache.log4j.spi.LoggingEvent

// Inspired by http://www.stichlberger.com/software/grails-log-to-database-with-custom-log4j-appender/
class SlackAppender extends org.apache.log4j.AppenderSkeleton implements org.apache.log4j.Appender {

    protected void append(LoggingEvent event) {
		event.getNDC();
		event.getThreadName();

		event.getMDCCopy();
		event.getLocationInformation();
		event.getRenderedMessage();
		event.getThrowableStrRep();
		String logStatement = getLayout().format(event);
		println logStatement
    }

	@Override
	public void close() { }

	@Override
	public boolean requiresLayout() {
		return true;
	}
}