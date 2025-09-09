package study.spring_advanced.proxy.app.v2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderControllerImplV2 implements OrderControllerV2 {

    private final OrderServiceV2 orderService;

    public OrderControllerImplV2(OrderServiceV2 orderService) {
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
