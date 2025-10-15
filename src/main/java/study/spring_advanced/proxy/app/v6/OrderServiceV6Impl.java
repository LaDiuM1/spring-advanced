package study.spring_advanced.proxy.app.v6;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV6Impl implements OrderServiceV6 {

    private final OrderRepositoryV6 orderRepository;

    public OrderServiceV6Impl(OrderRepositoryV6 orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
