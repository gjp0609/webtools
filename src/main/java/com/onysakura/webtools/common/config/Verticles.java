package com.onysakura.webtools.common.config;

import com.onysakura.webtools.verticle.*;
import io.vertx.core.Verticle;

public enum Verticles {

    LOG("/api", new LogVerticle()),
    AUTH("/api", new AuthVerticle()),
    PASTE_BIN("/api/paste", new PasteBinVerticle()),
    TEST("/api/test", new TestVerticle()),
    SQLITE("/sqlite", new DBVerticle()),
    ;

    private final String path;
    private final Verticle verticle;

    Verticles(String path, Verticle verticle) {
        this.path = path;
        this.verticle = verticle;
    }

    public String getPath() {
        return path;
    }

    public Verticle getVerticle() {
        return verticle;
    }
}
