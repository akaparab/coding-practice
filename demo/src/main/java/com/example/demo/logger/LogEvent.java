package com.example.demo.logger;

import java.time.Instant;

public class LogEvent {

        private final Instant timestamp;
        private final String loggerName;
        private final LogLevel level;
        private final String message;
        private final Throwable throwable;
        private final String threadName;

        public LogEvent(
                String loggerName,
                LogLevel level,
                String message,
                Throwable throwable) {

            this.timestamp = Instant.now();
            this.loggerName = loggerName;
            this.level = level;
            this.message = message;
            this.throwable = throwable;
            this.threadName = Thread.currentThread().getName();
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public String getLoggerName() {
            return loggerName;
        }

        public LogLevel getLevel() {
            return level;
        }

        public String getMessage() {
            return message;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public String getThreadName() {
            return threadName;
        }

}
