package study.spring_advanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring_advanced.trace.strategy.StrategyLogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final StrategyLogTrace logTrace;

    @GetMapping("/v4/request")
    public String request(String itemId) {
        logTrace.execute(() -> orderService.orderItem(itemId));
        return "OK";
    }

}
