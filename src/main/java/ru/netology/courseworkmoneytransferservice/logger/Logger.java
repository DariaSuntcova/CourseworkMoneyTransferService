package ru.netology.courseworkmoneytransferservice.logger;

public interface Logger {
    void info(String msg);
    void error(String msg);
    static Logger getInstance(){
        return null;
    }
}
