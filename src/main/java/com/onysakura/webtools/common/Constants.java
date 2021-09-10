package com.onysakura.webtools.common;

public interface Constants {

    String ARG_PREFIX = "--";

    String VERTX_CONFIG = "vertxConfig";

    interface EventBusAddress {
        String UserAuth = "web.auth.auth";
    }

    interface Status {
        int ok = 1;
        int fail = -1;
    }
}
