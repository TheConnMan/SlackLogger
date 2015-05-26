package com.theconnman.slacklogger;

import grails.util.Holders;
import org.apache.log4j.spi.LoggingEvent;
import grails.plugins.rest.client.RestBuilder;

class SlackMessages {

	static Map colors = [
		INFO: '#5f9ea0',
		TRACE: '#6f6d6d',
		DEBUG: '#b5dae9',
		WARN: '#ff9122',
		ERROR: '#ff4444',
		FATAL: '#b03e3c'
	];

	static public void postSlackMessage(LoggingEvent event, String logStatement) {
		def config = Holders.config.grails.plugin.slacklogger;
		String level = event.getLevel().toString();
		String attachmentColor = colors[level];
		Map field = [
			title: level,
			value: logStatement,
			short: false
		];
		RestBuilder rest = new RestBuilder();
		def resp = rest.post(config.webhook) {
			json {
				username = config.botName
				fallback = logStatement
				color = attachmentColor
				fields = [field]
				channel = config.channel
			}
		}
	}
}