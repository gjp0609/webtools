package com.onysakura.webtools.common.router;

import io.vertx.core.AbstractVerticle;

import java.util.ArrayList;
import java.util.List;

public abstract class RouterVerticle extends AbstractVerticle {

    protected List<RouterHandle> routers = new ArrayList<>();

    public List<RouterHandle> getRouters() {
        return routers;
    }
}
