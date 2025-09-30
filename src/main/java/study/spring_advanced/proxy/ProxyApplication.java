package study.spring_advanced.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import study.spring_advanced.proxy.config.*;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;
import study.spring_advanced.proxy.trace.logtrace.ThreadLocalLogTrace;

@Import({
		AppV0Config.class, AppV1Config.class,
        AppV2Config.class, AppV3Config.class,
        AppV4Config.class
})
@SpringBootApplication(scanBasePackages = {
        "study.spring_advanced.proxy.app.v4",
})
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

}
