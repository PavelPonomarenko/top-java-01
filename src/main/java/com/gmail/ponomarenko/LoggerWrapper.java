package com.gmail.ponomarenko;

import com.gmail.ponomarenko.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerWrapper {

    private Logger logger;

    public LoggerWrapper(Logger logger) {
        this.logger = logger;
    }

    public static LoggerWrapper get(Class aClass) {
        return new LoggerWrapper(LoggerFactory.getLogger(aClass));
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void info(String msg, Object... arguments) {
        logger.info(msg, arguments);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void warn(String msg, Throwable t) {
        logger.warn(msg, t);
    }

    public void error(String msg) {
        logger.error(msg);
    }

    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    public boolean isDebug() {
        return logger.isDebugEnabled();
    }

    public IllegalStateException getIllegalStateException(String msg) {
        return getIllegalStateException(msg);
    }

    public IllegalStateException getIllegalStateException(String msg, Throwable e) {
        logger.error(msg, e);
        return new IllegalStateException(msg, e);
    }

    public UnsupportedOperationException getUnsupportedOperationException(String msg) {
        logger.error(msg);
        return new UnsupportedOperationException(msg);
    }
    public NotFoundException getNotFoundException(String reason) {
        logger.error("Not data found ");
        return new NotFoundException(reason);
    }
}
