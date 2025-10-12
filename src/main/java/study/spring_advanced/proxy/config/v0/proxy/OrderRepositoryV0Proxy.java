package study.spring_advanced.proxy.config.v0.proxy;

import study.spring_advanced.proxy.app.v0.OrderRepositoryV0;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

public class OrderRepositoryV0Proxy implements OrderRepositoryV0 {

    private final OrderRepositoryV0 target;
    private final StrategyLogTrace trace;

    public OrderRepositoryV0Proxy(OrderRepositoryV0 target, StrategyLogTrace trace) {
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
