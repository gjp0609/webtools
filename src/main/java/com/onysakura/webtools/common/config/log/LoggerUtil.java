package com.onysakura.webtools.common.config.log;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class LoggerUtil {

    private static final ConsoleHandler handler;
    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private static final List<String> CONFIGS = Arrays.asList(
            "java.util.logging.ConsoleHandler.level=CONFIG",
            "java.util.logging.ConsoleHandler.formatter=com.onysakura.webtools.utils.CustomFormatter",
            ".level=CONFIG",
            "com.onysakura.level=ALL",
            "handlers=java.util.logging.ConsoleHandler"
    );

    static {
        handler = new ConsoleHandler();
        handler.setFormatter(new CustomFormatter());
        handler.setLevel(Level.ALL);
        try {
            byte[] bytes = String.join("\n", CONFIGS).getBytes(StandardCharsets.UTF_8);
            LogManager.getLogManager().readConfiguration(new BufferedInputStream(new ByteArrayInputStream(bytes)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Log getLogger(Class<?> clazz) {
        java.util.logging.Logger log = java.util.logging.Logger.getLogger(clazz.getName());
        log.setUseParentHandlers(false);
        log.addHandler(handler);
        return new Log(log);
    }

    public static class Log {
        private final Logger log;

        private Log(Logger log) {
            this.log = log;
        }

        public void debug(String msg, Object... args) {
            log.log(Level.FINE, msg, args);
        }

        public void info(String msg, Object... args) {
            log.log(Level.INFO, msg, args);
        }

        public void warn(String msg, Object... args) {
            log.log(Level.WARNING, msg, args);
        }

        public void warn(String msg, Throwable t) {
            log.log(Level.WARNING, msg, t);
        }

        public void error(String msg, Object... args) {
            log.log(Level.SEVERE, msg, args);
        }

        public void error(String msg, Throwable t) {
            log.log(Level.SEVERE, msg, t);
        }
    }

}
