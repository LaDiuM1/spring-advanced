package study.spring_advanced.proxy.app.proxy.v1;

import study.spring_advanced.proxy.app.v1.OrderRepositoryV1;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

public class OrderRepositoryV1Proxy extends OrderRepositoryV1 {

    private final OrderRepositoryV1 target;
    private final StrategyLogTrace trace;

    public OrderRepositoryV1Proxy(OrderRepositoryV1 target, StrategyLogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void save(String itemId) {
        trace.execute(() -> {
            target.save(itemId);
            return null;
        });
    }

}
