package com.onysakura.webtools.common.config;

public enum Configs {
    CONF_FILE("conf_file"),
    HTTP_PORT("http_port"),
    JWT_PRIVATE_KEY("jwt_private_key"),
    JWT_PUBLIC_KEY("jwt_public_key"),
    LOG_PATH("log_path");

    private final String key;

    Configs(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
