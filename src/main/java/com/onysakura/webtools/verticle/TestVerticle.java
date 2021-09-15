package com.onysakura.webtools.verticle;

import com.onysakura.webtools.common.config.log.LoggerUtil;
import com.onysakura.webtools.common.router.RouterHandle;
import com.onysakura.webtools.common.router.RouterVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;

public class TestVerticle extends RouterVerticle {

    private static final LoggerUtil.Log log = LoggerUtil.getLogger(TestVerticle.class);

    @Override
    public void start() throws Exception {
        routers.add(new RouterHandle(HttpMethod.POST, "/*", routingContext -> {
            JsonObject body = routingContext.getBodyAsJson();
            log.info("body: {}", body);
            routingContext.end("ok");
        }, true));
    }
}
