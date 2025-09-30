package study.spring_advanced.proxy.config.proxy.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring_advanced.proxy.app.v1.OrderControllerV1;
import study.spring_advanced.proxy.trace.strategy.StrategyLogTrace;

@RestController
public class OrderControllerV1Proxy extends OrderControllerV1 {

    private final OrderControllerV1 target;
    private final StrategyLogTrace trace;

    public OrderControllerV1Proxy(OrderControllerV1 target, StrategyLogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    @GetMapping("/v1/request")
    public String request(String itemId) {
        return trace.execute(() -> target.request(itemId));
    }

    @Override
    @GetMapping("/v1/no-log")
    public String noLog() {
        return target.noLog();
    }

}
