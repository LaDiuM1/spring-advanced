package study.spring_advanced.embedded_patterns.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring_advanced.embedded_patterns.trace.TraceStatus;
import study.spring_advanced.embedded_patterns.trace.hellotrace.TraceV1;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final TraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus traceStatus = null;

        try {
            traceStatus = trace.begin("OrderControllerV1.request");
            orderService.orderItem(itemId, traceStatus);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }

        return "OK";
    }

}
