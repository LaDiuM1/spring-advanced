package study.spring_advanced.proxy.config.v6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.config.v6.aspect.LogTraceAspect;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

@Configuration
public class AppV6Config {

    @Bean
    public LogTraceAspect advisorV6(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }

}
