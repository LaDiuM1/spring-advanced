package study.spring_advanced.proxy.app.v1;

public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;

    public OrderServiceV1(OrderRepositoryV1 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
