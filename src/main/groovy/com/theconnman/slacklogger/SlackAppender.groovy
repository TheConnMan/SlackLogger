package com.theconnman.slacklogger

import ch.qos.logback.classic.spi.LoggingEvent
import ch.qos.logback.core.UnsynchronizedAppenderBase

class SlackAppender extends UnsynchronizedAppenderBase {

	@Override
	protected void append(event) {
		LoggingEvent logEvent = (LoggingEvent)event
		try {
			SlackMessages.postSlackMessage(event, logEvent.formattedMessage)
		} catch (e) { }
	}
}
