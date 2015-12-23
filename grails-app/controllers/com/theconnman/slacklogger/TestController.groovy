package com.theconnman.slacklogger

class TestController {

    def index() {
		log.error 'Testing logger'
		render(status: 200)
	}
}
