package study.spring_advanced.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import study.spring_advanced.aop.app.order.OrderRepository;
import study.spring_advanced.aop.app.order.OrderService;
import study.spring_advanced.aop.config.aop.LogAspect;
import study.spring_advanced.aop.config.aop.TransactionAspect;

@Slf4j
@SpringBootTest(classes = AopApplication.class)
@Import({LogAspect.class, TransactionAspect.class})
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        Assertions.assertThrows(IllegalStateException.class, () -> orderService.orderItem("ex"));
    }
}
