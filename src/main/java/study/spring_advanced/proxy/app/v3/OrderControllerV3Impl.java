package study.spring_advanced.proxy.app.v3;

public class OrderControllerV3Impl implements OrderControllerV3 {

    private final OrderServiceV3 orderService;

    public OrderControllerV3Impl(OrderServiceV3 orderService) {
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
