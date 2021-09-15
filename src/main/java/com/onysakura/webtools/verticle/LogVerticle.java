package com.onysakura.webtools.verticle;

import com.onysakura.webtools.common.router.RouterHandle;
import com.onysakura.webtools.common.router.RouterVerticle;
import com.onysakura.webtools.common.config.log.LoggerUtil;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class LogVerticle extends RouterVerticle {
    private static final LoggerUtil.Log log = LoggerUtil.getLogger(LogVerticle.class);

    @Override
    public void start() throws Exception {

        routers.add(new RouterHandle("/*", routingContext -> {
            log.debug("request path: {}", routingContext.pathParams());
            log.debug("request headers: {}", getParams(routingContext.request().headers()));
            log.debug("request params: {}", getParams(routingContext.queryParams()));
//            log.debug("request body: {}", routingContext.getBodyAsJson().toString());
            routingContext.next();
        }));
    }

    public static HashMap<String, String> getParams(MultiMap multiMap) {
        HashMap<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : multiMap) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}
