package com.example.demo.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements Appender {

    private final Formatter formatter;
    private final String fileName;

    public FileAppender(
            String fileName,
            Formatter formatter) {

        this.fileName = fileName;
        this.formatter = formatter;
    }

    @Override
    public synchronized void append(LogEvent event) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(fileName, true))) {

            writer.write(formatter.format(event));
            writer.newLine();

        } catch (IOException e) {
            // In production, avoid recursively logging this
            // through the same logger.
            System.err.println(
                    "Failed to write log: " + e.getMessage());
        }
    }

}
