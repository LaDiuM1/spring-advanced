package study.spring_advanced.proxy.app.v6;

import org.springframework.stereotype.Component;

@Component
public class OrderControllerV6Impl implements OrderControllerV6 {

    private final OrderServiceV6 orderService;

    public OrderControllerV6Impl(OrderServiceV6 orderService) {
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
