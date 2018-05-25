package top.gjp0609.webtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WebtoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebtoolsApplication.class, args);
    }
}
