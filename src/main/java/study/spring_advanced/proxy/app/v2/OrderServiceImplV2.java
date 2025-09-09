package study.spring_advanced.proxy.app.v2;

public class OrderServiceImplV2 implements OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;

    public OrderServiceImplV2(OrderRepositoryV2 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
