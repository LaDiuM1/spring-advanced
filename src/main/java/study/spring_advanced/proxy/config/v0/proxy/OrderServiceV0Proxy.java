package study.spring_advanced.proxy.config.v0.proxy;

import study.spring_advanced.proxy.app.v0.OrderServiceV0;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

public class OrderServiceV0Proxy implements OrderServiceV0 {

    private final OrderServiceV0 target;
    private final StrategyLogTrace trace;

    public OrderServiceV0Proxy(OrderServiceV0 target, StrategyLogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void orderItem(String itemId) {
        trace.execute(() -> {
            target.orderItem(itemId);
            return null;
        });
    }
}
