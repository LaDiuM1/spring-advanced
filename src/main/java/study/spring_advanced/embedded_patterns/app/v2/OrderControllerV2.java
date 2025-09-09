package study.spring_advanced.embedded_patterns.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring_advanced.embedded_patterns.trace.TraceStatus;
import study.spring_advanced.embedded_patterns.trace.logtrace.LogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final LogTrace trace;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        TraceStatus traceStatus = null;

        try {
            traceStatus = trace.begin("OrderControllerImplV2.request");
            orderService.orderItem(itemId);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }

        return "OK";
    }

}
