package study.spring_advanced.proxy.app.v5;

import org.springframework.stereotype.Component;

@Component
public class OrderControllerV5Impl implements OrderControllerV5 {

    private final OrderServiceV5 orderService;

    public OrderControllerV5Impl(OrderServiceV5 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "OK";
    }

    @Override
    public String noLog() {
        return "OK";
    }

}
