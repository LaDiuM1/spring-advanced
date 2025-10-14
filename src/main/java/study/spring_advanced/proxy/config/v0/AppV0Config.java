package study.spring_advanced.proxy.config.v0;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.config.v0.proxy.OrderControllerV0Proxy;
import study.spring_advanced.proxy.config.v0.proxy.OrderRepositoryV0Proxy;
import study.spring_advanced.proxy.config.v0.proxy.OrderServiceV0Proxy;
import study.spring_advanced.proxy.app.v0.*;
import study.spring_advanced.proxy.trace.logtrace.ThreadLocalLogTrace;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

@Configuration
public class AppV0Config {

    @Bean
    public OrderControllerV0 orderControllerV0(StrategyLogTrace logTrace) {
        OrderControllerV0Impl orderController = new OrderControllerV0Impl(orderServiceV0(logTrace));

        return new OrderControllerV0Proxy(orderController, logTrace);
    }

    @Bean
    public OrderServiceV0 orderServiceV0(StrategyLogTrace logTrace) {
        OrderServiceV0Impl orderService = new OrderServiceV0Impl(orderRepositoryV0(logTrace));
        return new OrderServiceV0Proxy(orderService, logTrace);
    }

    @Bean
    public OrderRepositoryV0 orderRepositoryV0(StrategyLogTrace logTrace) {
        OrderRepositoryV0Impl orderRepository = new OrderRepositoryV0Impl();
        return new OrderRepositoryV0Proxy(orderRepository, logTrace);
    }

}
