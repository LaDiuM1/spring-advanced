package study.spring_advanced.proxy.config.v1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.app.v1.OrderControllerV1;
import study.spring_advanced.proxy.app.v1.OrderRepositoryV1;
import study.spring_advanced.proxy.app.v1.OrderServiceV1;
import study.spring_advanced.proxy.config.v1.proxy.OrderControllerV1Proxy;
import study.spring_advanced.proxy.config.v1.proxy.OrderRepositoryV1Proxy;
import study.spring_advanced.proxy.config.v1.proxy.OrderServiceV1Proxy;
import study.spring_advanced.proxy.trace.logtrace.ThreadLocalLogTrace;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerV1 orderControllerV1(StrategyLogTrace logTrace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1(orderServiceV1(logTrace));
        return new OrderControllerV1Proxy(orderControllerV1, logTrace);
    }

    @Bean
    public OrderServiceV1 orderServiceV1(StrategyLogTrace logTrace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1(orderRepositoryV1(logTrace));
        return new OrderServiceV1Proxy(orderServiceV1, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(StrategyLogTrace logTrace) {
        OrderRepositoryV1 orderRepositoryV1 = new OrderRepositoryV1();
        return new OrderRepositoryV1Proxy(orderRepositoryV1, logTrace);
    }

}
