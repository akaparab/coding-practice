package com.example.demo.logger;

public class ConsoleAppender implements Appender {

    private final Formatter formatter;

    public ConsoleAppender(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogEvent event) {
        System.out.println(formatter.format(event));
    }

}
