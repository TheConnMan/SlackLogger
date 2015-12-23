import grails.util.Environment
import com.theconnman.slacklogger.SlackAppender

def loc = ['../UserConfig.groovy', 'webapps/ROOT/Jenkins.groovy'].grep { new File(it).exists() }.first();
def localConfig = new ConfigSlurper(Environment.current.name).parse(new File(loc).toURI().toURL())

grails.app.context = '/'

grails {
	plugin {
		slacklogger {
			webhook = localConfig.slacklogger.webhook
			botName = 'Test Bot'
			channel = '#testing'
		}
	}
}