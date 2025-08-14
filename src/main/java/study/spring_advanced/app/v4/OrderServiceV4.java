package study.spring_advanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring_advanced.app.v3.OrderRepositoryV3;
import study.spring_advanced.trace.logtrace.LogTrace;
import study.spring_advanced.trace.strategy.StrategyLogTrace;
import study.spring_advanced.trace.template.AbstractLogTraceTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final StrategyLogTrace logTrace;

    public void orderItem(String itemId) {
        logTrace.execute(() -> orderRepository.save(itemId));
    }

}
