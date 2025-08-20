package study.spring_advanced.embedded_patterns.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring_advanced.embedded_patterns.trace.strategy.StrategyLogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final StrategyLogTrace logTrace;

    public void orderItem(String itemId) {
        logTrace.execute(() -> orderRepository.save(itemId));
    }

}
