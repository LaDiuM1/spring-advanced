package study.spring_advanced.proxy.config.v1.proxy;

import study.spring_advanced.proxy.app.v1.OrderServiceV1;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

public class OrderServiceV1Proxy extends OrderServiceV1 {

    private final OrderServiceV1 target;
    private final StrategyLogTrace trace;

    public OrderServiceV1Proxy(OrderServiceV1 target, StrategyLogTrace trace) {
        super(null);
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
