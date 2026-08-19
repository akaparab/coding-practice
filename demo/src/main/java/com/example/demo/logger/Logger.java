package com.example.demo.logger;

import lombok.Setter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {

    private final String name;

    @Setter
    private volatile LogLevel level;

    private final List<Appender> appenders =
            new CopyOnWriteArrayList<>();

    Logger(String name, LogLevel level) {
        this.name = name;
        this.level = level;
    }

    public void addAppender(Appender appender) {
        appenders.add(appender);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message, null);
    }

    public void info(String message) {
        log(LogLevel.INFO, message, null);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message, null);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message, null);
    }

    public void error(
            String message,
            Throwable throwable) {

        log(LogLevel.ERROR, message, throwable);
    }

    private void log(
            LogLevel eventLevel,
            String message,
            Throwable throwable) {

        if (!isEnabled(eventLevel)) {
            return;
        }

        LogEvent event = new LogEvent(
                name,
                eventLevel,
                message,
                throwable
        );

        for (Appender appender : appenders) {
            appender.append(event);
        }
    }

    private boolean isEnabled(LogLevel eventLevel) {

        return eventLevel.getPriority()
                >= level.getPriority();
    }

}
