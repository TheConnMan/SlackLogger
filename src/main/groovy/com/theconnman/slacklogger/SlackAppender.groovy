package com.theconnman.slacklogger

import ch.qos.logback.classic.spi.LoggingEvent
import ch.qos.logback.core.UnsynchronizedAppenderBase
import ch.qos.logback.core.encoder.Encoder
import org.apache.commons.io.output.ByteArrayOutputStream

class SlackAppender extends UnsynchronizedAppenderBase {

	Encoder encoder

	@Override
	protected void append(event) {
		LoggingEvent logEvent = (LoggingEvent)event
		ByteArrayOutputStream output = new ByteArrayOutputStream()
		encoder.init(output)
		encoder.doEncode(logEvent)
		encoder.close()
		String logStatement = output.toString('UTF-8')
		try {
			SlackMessages.postSlackMessage((LoggingEvent)event, logStatement)
		} catch (e) { e.printStackTrace() }
	}
}
