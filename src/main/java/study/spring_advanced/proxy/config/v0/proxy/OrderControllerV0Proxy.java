package study.spring_advanced.proxy.config.v0.proxy;

import study.spring_advanced.proxy.app.v0.OrderControllerV0;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

public class OrderControllerV0Proxy implements OrderControllerV0 {

    private final OrderControllerV0 target;
    private final StrategyLogTrace trace;

    public OrderControllerV0Proxy(OrderControllerV0 target, StrategyLogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {
        return trace.execute(() -> target.request(itemId));
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
