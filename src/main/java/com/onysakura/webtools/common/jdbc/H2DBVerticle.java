package com.onysakura.webtools.common.jdbc;

import com.onysakura.webtools.common.router.RouterVerticle;
import com.onysakura.webtools.config.log.LoggerUtil;
import io.vertx.core.json.JsonObject;
import io.vertx.jdbcclient.JDBCConnectOptions;
import io.vertx.jdbcclient.JDBCPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;

public class H2DBVerticle extends RouterVerticle {
    private static final LoggerUtil.Log log = LoggerUtil.getLogger(H2DBVerticle.class);

    @Override
    public void start() throws Exception {
        JsonObject dataSource = config().getJsonObject("dataSource");
//        JDBCPool pool = JDBCPool.pool(
//                vertx,
//                new JDBCConnectOptions()
//                        .setJdbcUrl(dataSource.getString("url"))
//                        .setUser(dataSource.getString("user"))
//                        .setPassword(dataSource.getString("password")),
//                new PoolOptions()
//                        .setMaxSize(16)
//        );
//        pool.query("select * from API").execute().onSuccess(rows -> {
//            for (Row row : rows) {
//                System.out.println(row.toJson().toString());
//            }
//        }).onFailure(t -> {
//            log.warn("query err", t);
//        });
    }
}
