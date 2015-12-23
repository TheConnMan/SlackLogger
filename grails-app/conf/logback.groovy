import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import com.theconnman.slacklogger.SlackAppender
import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
		pattern = "%level %logger - %msg%n"
	}
}

appender('SLACK', SlackAppender) {
	encoder(PatternLayoutEncoder) {
		pattern = "%logger - %msg%n"
	}
}

root(ERROR, ['STDOUT', 'SLACK'])

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
	appender("FULL_STACKTRACE", FileAppender) {
		file = "${targetDir}/stacktrace.log"
		append = true
		encoder(PatternLayoutEncoder) {
			pattern = "%level %logger - %msg%n"
		}
	}
	logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}
