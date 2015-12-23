package com.theconnman.slacklogger

class SlackAppender extends ch.qos.logback.core.UnsynchronizedAppenderBase {

	@Override
	protected void append(event) {
		println 'Logging event...'
	}
}
