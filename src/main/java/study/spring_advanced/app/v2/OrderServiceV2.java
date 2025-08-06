package study.spring_advanced.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring_advanced.app.v1.OrderRepositoryV1;
import study.spring_advanced.trace.TraceStatus;
import study.spring_advanced.trace.hellotrace.TraceV1;
import study.spring_advanced.trace.logtrace.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus traceStatus = null;

        try {
            traceStatus = trace.begin("OrderServiceV2.orderItem");
            orderRepository.save(itemId);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }

}
