package com.onysakura.webtools.config;

public enum Configs {
    HTTP_PORT("http_port"),
    LOG_PATH("log_path");

    private final String key;

    Configs(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
