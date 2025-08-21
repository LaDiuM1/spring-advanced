package study.spring_advanced.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.app.v0.*;

@Configuration
public class AppV0Config {

    @Bean
    public OrderControllerV0 orderControllerV0() {
        return new OrderControllerV0Impl(orderServiceV0());
    }

    @Bean
    public OrderServiceV0 orderServiceV0() {
        return new OrderServiceV0Impl(orderRepositoryV0());
    }

    @Bean
    public OrderRepositoryV0 orderRepositoryV0() {
        return new OrderRepositoryV0Impl();
    }

}
