import com.theconnman.slacklogger.SlackAppender

def loc = ['../UserConfig.groovy', 'webapps/ROOT/Jenkins.groovy'].grep { new File(it).exists() }.first();
def localConfig = new ConfigSlurper(grailsSettings.grailsEnv).parse(new File(loc).toURI().toURL())

grails.app.context = '/'

log4j = {
	appenders {
		console name: 'stdout', threshold: org.apache.log4j.Level.ERROR
		appender new SlackAppender(name: 'slackAppender', layout: pattern(conversionPattern: '%c{2} - %m%n'), threshold: org.apache.log4j.Level.INFO)
	}

	info 'slackAppender' : [
		'grails.app.controllers.com.theconnman.slacklogger'
	]

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
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