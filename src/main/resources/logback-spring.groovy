scan()

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "| %highlight(%-5level) | %d{HH:mm:ss.SSS} | %logger - %msg%n"
    }
}

logger("org.oasis_eu.spring", DEBUG)
logger("org.ozwillo.dcexporter", INFO)
logger("org.oasis_eu.spring.util.KernelLoggingInterceptor", INFO) // ERROR, WARN (prod), INFO (preprod, dev), DEBUG
logger("kernelLogging.logFullErrorResponses", INFO) // DEBUG logs any response, INFO only errors
logger("eu.trentorise.opendata.jackan",	INFO)

root(WARN, ["CONSOLE"])
