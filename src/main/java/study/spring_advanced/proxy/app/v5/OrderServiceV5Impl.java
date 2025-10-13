package study.spring_advanced.proxy.app.v5;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5Impl implements OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;

    public OrderServiceV5Impl(OrderRepositoryV5 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
