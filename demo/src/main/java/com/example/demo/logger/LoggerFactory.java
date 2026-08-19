package com.example.demo.logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LoggerFactory {

    private static final ConcurrentMap<String, Logger>
            LOGGERS = new ConcurrentHashMap<>();

    private static volatile LogLevel globalLevel =
            LogLevel.INFO;

    private static volatile Formatter formatter =
            new DefaultFormatter();

    private static volatile Appender defaultAppender =
            new ConsoleAppender(formatter);

    public static Logger getLogger(Class<?> clazz) {

        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String name) {

        return LOGGERS.computeIfAbsent(
                name,
                key -> {

                    Logger logger =
                            new Logger(key, globalLevel);

                    logger.addAppender(defaultAppender);

                    return logger;
                }
        );
    }

    public static void setGlobalLevel(LogLevel level) {
        globalLevel = level;

        LOGGERS.values()
                .forEach(logger -> logger.setLevel(level));
    }
}
