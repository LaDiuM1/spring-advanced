package study.spring_advanced.proxy.app.v3;

public class OrderServiceV3Impl implements OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;

    public OrderServiceV3Impl(OrderRepositoryV3 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
