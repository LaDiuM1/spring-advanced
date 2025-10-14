package study.spring_advanced.proxy.config.v5;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.config.v3.LogTraceAdvice;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

@Configuration
public class AppV5Config {

    @Bean
    public Advisor advisorV5(LogTrace logTrace) {
        // pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("""
                    execution(* study.spring_advanced.proxy.app.v5..*(..)) &&
                    !execution(* study.spring_advanced.proxy.app.v5..noLog(..))
                """);

        return new DefaultPointcutAdvisor(pointcut, new LogTraceAdvice(logTrace));
    }

}
