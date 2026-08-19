package com.example.demo.logger;

public class DefaultFormatter implements Formatter {

    @Override
    public String format(LogEvent event) {

        String result = String.format(
                "%s [%s] %s %s - %s",
                event.getTimestamp(),
                event.getThreadName(),
                event.getLevel(),
                event.getLoggerName(),
                event.getMessage()
        );

        if (event.getThrowable() != null) {
            result += System.lineSeparator()
                    + stackTrace(event.getThrowable());
        }

        return result;
    }

    private String stackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();

        sb.append(throwable).append(System.lineSeparator());

        for (StackTraceElement element :
                throwable.getStackTrace()) {

            sb.append("\tat ")
                    .append(element)
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

}
