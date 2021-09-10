package com.onysakura.webtools.web;

import com.onysakura.webtools.common.Constants;
import com.onysakura.webtools.common.dto.Msg;
import com.onysakura.webtools.common.router.RouterHandle;
import com.onysakura.webtools.common.router.RouterVerticle;
import com.onysakura.webtools.config.Configs;
import com.onysakura.webtools.config.log.LoggerUtil;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class AuthServer extends RouterVerticle {

    private static final LoggerUtil.Log log = LoggerUtil.getLogger(AuthServer.class);
    private static final String ALGORITHM = "RS512";

    private JWTAuth auth;

    @Override
    public void start() {
        routers.add(new RouterHandle(HttpMethod.GET, "/login", this::login));
        JWTAuthOptions jwtAuthOptions = new JWTAuthOptions()
                .addPubSecKey(new PubSecKeyOptions().setAlgorithm(ALGORITHM).setBuffer(config().getString(Configs.JWT_PUBLIC_KEY.key())))
                .addPubSecKey(new PubSecKeyOptions().setAlgorithm(ALGORITHM).setBuffer(config().getString(Configs.JWT_PRIVATE_KEY.key())));
        auth = JWTAuth.create(vertx, jwtAuthOptions);
        vertx.eventBus().consumer(Constants.EventBusAddress.UserAuth, this::auth);
    }

    public void login(RoutingContext context) {
        List<String> username = context.queryParam("username");
        List<String> password = context.queryParam("password");
        // match username and password
        if (true) {
            String token = auth.generateToken(
                    new JsonObject().put("username", username),
                    new JWTOptions().setAlgorithm(ALGORITHM).setExpiresInSeconds(30)
            );
            context.json(Msg.ok().put("token", token));
        } else {
            context.json(Msg.fail("password incorrect"));
        }
    }

    public void auth(Message<JsonObject> message) {
        log.info("EventBus [{}], message: {}", message.address(), message.body());
        String token = message.body().getString("token");
        auth.authenticate(new JsonObject().put("token", token))
                .onSuccess(user -> message.reply(Msg.ok().put("username", user.principal().getValue("username"))))
                .onFailure(err -> message.reply(Msg.fail()));

    }

}
