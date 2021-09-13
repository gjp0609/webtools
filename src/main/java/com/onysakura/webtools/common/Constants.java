package com.onysakura.webtools.common;

public interface Constants {

    String ARG_PREFIX = "--";

    String VERTX_CONFIG = "vertxConfig";

    interface EventBusAddress {
        // Database
        String DB_USER_SELECT = "db.user.select";
        String DB_PASTE_SELECT = "db.paste.select";
        String DB_PASTE_INSERT = "db.paste.insert";
    }

    interface Status {
        int ok = 1;
        int fail = -1;
    }
}
