package ru.netology.courseworkmoneytransferservice.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerImpl implements Logger {
    private static Logger instance = null;
    private FileWriter writer;

    private LoggerImpl() {
        try {
            writer = new FileWriter("log.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new LoggerImpl();
        }
        return instance;
    }

    @Override
    public void info(String msg) {
        log(LogLvl.INFO, msg);
    }

    @Override
    public void error(String msg) {
        log(LogLvl.ERROR, msg);
    }

    private void log(LogLvl lvl, String msg) {
        try {
            String text = "[" + lvl + "] " + LocalDateTime.now() + " === " + msg;
            writer.write(text);
            writer.append('\n');
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
