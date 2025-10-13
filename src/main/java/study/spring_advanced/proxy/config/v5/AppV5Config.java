package study.spring_advanced.proxy.config.v5;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.config.v3.LogTraceAdvice;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

@Configuration
@ComponentScan(basePackages = {
        "study.spring_advanced.proxy.app.v5",
})
public class AppV5Config {

    @Bean
    public Advisor advisorV5(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        pointcut.setClassFilter(c -> c.getPackageName().startsWith("study.spring_advanced.proxy.app.v5"));

        return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
    }

}
