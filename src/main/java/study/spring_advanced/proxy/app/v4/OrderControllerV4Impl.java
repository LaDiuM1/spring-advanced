package study.spring_advanced.proxy.app.v4;

import org.springframework.stereotype.Component;

@Component
public class OrderControllerV4Impl implements OrderControllerV4 {

    private final OrderServiceV4 orderService;

    public OrderControllerV4Impl(OrderServiceV4 orderService) {
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
