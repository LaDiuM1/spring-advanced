package study.spring_advanced.proxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.spring_advanced.proxy.app.v1.OrderControllerV1;
import study.spring_advanced.proxy.app.v1.OrderRepositoryV1;
import study.spring_advanced.proxy.app.v1.OrderServiceV1;

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerV1 orderControllerV1() {
        return new OrderControllerV1(orderServiceV1());
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        return new OrderServiceV1(orderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        return new OrderRepositoryV1();
    }

}
