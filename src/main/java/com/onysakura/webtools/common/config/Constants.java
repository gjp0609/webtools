package com.onysakura.webtools.common.config;

public interface Constants {

    String ARG_PREFIX = "--";

    String VERTX_CONFIG = "vertxConfig";

    interface Status {
        int OK = 1;
        int FAIL = -1;
        int INVALID_TOKEN = -200;
    }
}
