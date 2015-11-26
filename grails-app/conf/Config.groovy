import grails.util.Environment
import com.theconnman.slacklogger.SlackAppender

def loc = ['../UserConfig.groovy', 'webapps/ROOT/Jenkins.groovy'].grep { new File(it).exists() }.first();
def localConfig = new ConfigSlurper(Environment.current.name).parse(new File(loc).toURI().toURL())

grails.app.context = '/'

log4j = {
	appenders {
		console name: 'stdout', threshold: org.apache.log4j.Level.ERROR
		appender new SlackAppender(name: 'slackAppender', layout: pattern(conversionPattern: '%c{2} - %m%n'), threshold: org.apache.log4j.Level.INFO)
	}

	info 'slackAppender' : [
		'grails.app.controllers.com.theconnman.slacklogger'
	]

	error 'org.codehaus.groovy.grails',
			'org.springframework',
			'org.hibernate',
			'net.sf.ehcache.hibernate'
}

grails {
	plugin {
		slacklogger {
			webhook = localConfig.slacklogger.webhook
			botName = 'Test Bot'
			channel = '#testing'
		}
	}
}