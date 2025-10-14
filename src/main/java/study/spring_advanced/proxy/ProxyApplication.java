package study.spring_advanced.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import study.spring_advanced.proxy.config.v0.AppV0Config;
import study.spring_advanced.proxy.config.v1.AppV1Config;
import study.spring_advanced.proxy.config.v2.AppV2Config;
import study.spring_advanced.proxy.config.v3.AppV3Config;
import study.spring_advanced.proxy.config.v4.AppV4Config;
import study.spring_advanced.proxy.config.v5.AppV5Config;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;
import study.spring_advanced.proxy.trace.logtrace.ThreadLocalLogTrace;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

@Import({
		AppV0Config.class, AppV1Config.class,
        AppV2Config.class, AppV3Config.class,
        AppV4Config.class, AppV5Config.class
})
@SpringBootApplication(scanBasePackages = {
        "study.spring_advanced.proxy.trace",
})
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

    @Bean
    public StrategyLogTrace strategyLogTrace() {
        return new StrategyLogTrace(new ThreadLocalLogTrace());
    }

}
