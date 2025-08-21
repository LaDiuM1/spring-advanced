package study.spring_advanced.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.app.v0.*;

@Configuration
public class AppV0Config {

    @Bean
    public OrderControllerV0 orderController() {
        return new OrderControllerV0Impl(orderService());
    }

    @Bean
    public OrderServiceV0 orderService() {
        return new OrderServiceV0Impl(orderRepository());
    }

    @Bean
    public OrderRepositoryV0 orderRepository() {
        return new OrderRepositoryV0Impl();
    }

}
