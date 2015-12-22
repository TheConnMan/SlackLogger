package slacklogger

import grails.plugins.*

class SlackLoggerGrailsPlugin extends Plugin {
	def grailsVersion = "3.0.0 > *"
	def pluginExcludes = [
		"grails-app/controllers/*",
		"grails-app/views/*",
		"resources/*"
	]
	def title = "Slack Logger"
	def author = "TheConnMan"
	def authorEmail = "brian@theconnman.com"
	def description = 'Slack Logger is a custom log4j appender which logs directly to a Slack channel.'
	def profiles = ['web']
	def documentation = "https://github.com/TheConnMan/SlackLogger"
	def license = "MIT"
	def issueManagement = [ system: "GitHub", url: "https://github.com/TheConnMan/SlackLogger/issues" ]
	def scm = [ url: "https://github.com/TheConnMan/SlackLogger" ]
}
