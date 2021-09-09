package com.onysakura.webtools.common.router;

import java.util.ArrayList;
import java.util.List;


public interface RouterVerticle {

    List<RouterHandle> routers = new ArrayList<>();

    default List<RouterHandle> getRouters() {
        return routers;
    }
}
