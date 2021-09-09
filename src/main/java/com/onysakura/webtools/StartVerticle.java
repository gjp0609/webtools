package com.onysakura.webtools;

import com.onysakura.webtools.common.Constants;
import com.onysakura.webtools.common.Verticles;
import com.onysakura.webtools.common.router.RouterVerticle;
import com.onysakura.webtools.config.Configs;
import com.onysakura.webtools.config.log.LoggerUtil;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;

public class StartVerticle {

    private static final LoggerUtil.Log log = LoggerUtil.getLogger(StartVerticle.class);

    public static void main(String[] args) {
        long serverStartTime = System.currentTimeMillis();
        Vertx vertx = Vertx.vertx(new VertxOptions()
                .setAddressResolverOptions(new AddressResolverOptions()
                        .addServer("223.5.5.5") // alibaba dns
                        .addServer("119.29.29.29") // tencent dns
                )
        );
        ConfigRetriever retriever = ConfigRetriever.create(vertx, initOptions(args));
        retriever.getConfig(ar -> {
            if (ar.succeeded()) {
                log.info("init ConfigRetriever success.");
                JsonObject configs = ar.result().getJsonObject(Constants.VERTX_CONFIG);
                DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(configs);
                String port = configs.getString(Configs.HTTP_PORT.key(), "8080");
                Router router = Router.router(vertx);
                // 创建服务
                vertx.createHttpServer()
                        .requestHandler(router)
                        .listen(Integer.parseInt(port))
                        .onSuccess(server -> {
                                    for (Verticles item : Verticles.values()) {
                                        try {
                                            Verticle verticle = item.getVerticle();
                                            vertx.deployVerticle(verticle, deploymentOptions).onComplete(res -> {
                                                RouterVerticle routerVerticle = (RouterVerticle) verticle;
                                                Router subRouter = Router.router(vertx);
                                                routerVerticle.getRouters().forEach(routers -> {
                                                    log.info("route bind: {} -> {}", routers.getHttpMethod(), routers.getPath());
                                                    if (routers.getHttpMethod() == null) {
                                                        subRouter.route(routers.getPath()).handler(routers.getHandler());
                                                    } else {
                                                        subRouter.route(routers.getHttpMethod(), routers.getPath()).handler(routers.getHandler());
                                                    }
                                                });
                                                router.mountSubRouter(item.getPath(), subRouter);
                                            });
                                        } catch (Exception e) {
                                            log.error("init Verticles error.", e);
                                        }
                                    }
                                    long startTimeUsage = System.currentTimeMillis() - serverStartTime;
                                    log.info("HTTP server started on port {} in {} seconds.", server.actualPort(), String.format("%.3f", startTimeUsage / 1000D));
                                }
                        ).onFailure(t -> {
                            log.error("HTTP server started failed.", t);
                        });
            } else {
                log.warn("init ConfigRetriever success!");
            }
        });
    }

    private static ConfigRetrieverOptions initOptions(String[] args) {
        log.info("initOptions: {}", Arrays.toString(args));
        // 使用默认ConfigStore
        ConfigRetrieverOptions options = new ConfigRetrieverOptions().setIncludeDefaultStores(true);
        // 禁用配置刷新
        options.setScanPeriod(-1);
        options.addStore(new ConfigStoreOptions()
                .setType("json")
                .setOptional(true)
                .setConfig(new JsonObject().put(Constants.VERTX_CONFIG, parseArgs(args)))
        );
        return options;
    }

    public static JsonObject parseArgs(String[] args) {
        JsonObject config = new JsonObject();
        HashMap<String, Configs> configKeySet = new HashMap<>();
        for (Configs configItem : Configs.values()) {
            String configKey = Constants.ARG_PREFIX + configItem.key();
            configKeySet.put(configKey, configItem);
        }
        for (String arg : args) {
            if (StringUtils.isNotBlank(arg) && arg.startsWith(Constants.ARG_PREFIX) && arg.contains("=")) {
                String[] split = arg.split("=");
                String key = split[0];
                String value = split[1];
                if (configKeySet.containsKey(key)) {
                    config.put(configKeySet.get(key).key(), value);
                }
            }
        }
        log.info("configs: {}", config.toString());
        return config;
    }
}
