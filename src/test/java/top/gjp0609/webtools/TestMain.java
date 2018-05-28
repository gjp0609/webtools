package top.gjp0609.webtools;

import top.gjp0609.webtools.utils.HttpUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(500);
        for (int i = 0; i < 1000; i++) {
            pool.submit(new MyTh(i));
        }
    }

    static class MyTh implements Callable<Object> {
        private int name;

        MyTh(int name) {
            this.name = name;
        }

        @Override
        public Object call() throws Exception {
            for (int i = 0; i < 10; i++) {
                HttpUtil.doGet("http://localhost:8083/user/getUser/3");
            }
            return null;
        }
    }
}
