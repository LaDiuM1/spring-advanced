package study.spring_advanced.embedded_patterns.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring_advanced.embedded_patterns.trace.logtrace.LogTrace;
import study.spring_advanced.embedded_patterns.trace.template.AbstractLogTraceTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId) {

        new AbstractLogTraceTemplate(trace) {
            @Override
            public void call() {
                orderService.orderItem(itemId);
            }
        }.execute();

        return "OK";
    }

}
