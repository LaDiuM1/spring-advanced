package study.spring_advanced.proxy.app.v0;

public class OrderControllerV0Impl implements OrderControllerV0 {

    private final OrderServiceV0 orderService;

    public OrderControllerV0Impl(OrderServiceV0 orderService) {
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
