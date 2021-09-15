package com.onysakura.webtools.common.config.log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;

public class CustomFormatter extends Formatter {
    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private static final int CLASS_NAME_LENGTH = 40;

    private static final ConcurrentHashMap<Long, String> THREAD_MAP = new ConcurrentHashMap<>();

    @Override
    public String format(LogRecord record) {
        Instant instant = record.getInstant();
        String time = LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), ZoneOffset.ofHours(8)).format(pattern);
        int threadID = record.getThreadID();
        String threadName = THREAD_MAP.get((long) threadID);
        if (threadName == null) {
            for (Thread thread : Thread.getAllStackTraces().keySet()) {
                THREAD_MAP.putIfAbsent(thread.getId(), thread.getName());
                if (thread.getId() == threadID) {
                    threadName = thread.getName();
                    break;
                }
            }
        }
        String level = getLevel(record.getLevel());
        String shortenClassName = shortenClassName(record.getLoggerName());
        String recordMessage = record.getMessage();
        recordMessage = record.getParameters() == null ? recordMessage : format(recordMessage, record.getParameters());
        String thrownString = "";
        Throwable thrown = record.getThrown();
        if (thrown != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            thrown.printStackTrace(new PrintStream(byteArrayOutputStream));
            String stackTrace = String.valueOf(byteArrayOutputStream);
            thrownString = stackTrace + "\n";
        }
        return String.format("\033[36m%s \033[30m[%26s] %5s \033[35m[%s] \033[30m%s\n\033[31m%s\033[0m", time, threadName, level, shortenClassName, recordMessage, thrownString);
    }

    public static String getLevel(Level level) {
        String name = level.getName();
        if ("SEVERE".equals(name)) {
            return "ERROR";
        } else if ("WARNING".equals(name)) {
            return "WARN";
        } else if (name.startsWith("FIN")) {
            return "DEBUG";
        }
        return level.getName();
    }

    public static String shortenClassName(String className) {
        String shorten = className;
        if (className.length() > CLASS_NAME_LENGTH) {
            StringBuilder s = new StringBuilder();
            String[] split = className.split("\\.");
            for (int i = 0; i < split.length; i++) {
                if (i < split.length - 1) {
                    s.append(split[i].charAt(0));
                    s.append(".");
                } else {
                    s.append(split[i]);
                }
            }
            shorten = s.toString();
        }
        if (shorten.length() <= CLASS_NAME_LENGTH) {
            shorten = String.format("%" + CLASS_NAME_LENGTH + "s", shorten);
        }
        return shorten;
    }

    public static String format(String msg, Object... args) {
        for (Object arg : args) {
            msg = msg.replaceFirst("\\{}", String.valueOf(Matcher.quoteReplacement(String.valueOf(arg))));
        }
        return msg;
    }
}
