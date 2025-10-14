package study.spring_advanced.proxy.config.v4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.config.v3.LogTraceAdvice;
import study.spring_advanced.proxy.config.v4.postprocessor.PackageLogTracePostProcessor;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

@Slf4j
@Configuration
public class AppV4Config {

    @Bean
    public PackageLogTracePostProcessor packageLogTracePostProcessor(LogTrace logTrace) {
        return new PackageLogTracePostProcessor("study.spring_advanced.proxy.app.v4", getAdvisor(logTrace));
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
    }

}
