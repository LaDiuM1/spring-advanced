package study.spring_advanced.proxy.app.v4;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV4Impl implements OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;

    public OrderServiceV4Impl(OrderRepositoryV4 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
