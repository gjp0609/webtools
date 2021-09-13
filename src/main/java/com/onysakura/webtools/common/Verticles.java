package com.onysakura.webtools.common;

import com.onysakura.webtools.verticle.AuthVerticle;
import com.onysakura.webtools.verticle.DBVerticle;
import com.onysakura.webtools.verticle.LogVerticle;
import com.onysakura.webtools.verticle.PasteBinVerticle;
import io.vertx.core.Verticle;

public enum Verticles {

    LOG("/api", new LogVerticle()),
    AUTH("/api/auth", new AuthVerticle()),
    ShortUrl("/api/auth/paste", new PasteBinVerticle()),
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
