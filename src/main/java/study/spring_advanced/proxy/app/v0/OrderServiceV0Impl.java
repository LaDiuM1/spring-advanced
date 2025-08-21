package study.spring_advanced.proxy.app.v0;

public class OrderServiceV0Impl implements OrderServiceV0 {

    private final OrderRepositoryV0 orderRepository;

    public OrderServiceV0Impl(OrderRepositoryV0 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
