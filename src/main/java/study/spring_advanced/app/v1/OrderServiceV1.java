package study.spring_advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring_advanced.trace.TraceStatus;
import study.spring_advanced.trace.hellotrace.TraceV1;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final TraceV1 trace;

    public void orderItem(String itemId, TraceStatus traceStatus) {
        TraceStatus nextStatus = traceStatus;

        try {
            nextStatus = trace.next(traceStatus, "OrderServiceV1.orderItem");
            orderRepository.save(itemId, nextStatus);
            trace.end(nextStatus);
        } catch (Exception e) {
            trace.exception(nextStatus, e);
            throw e;
        }
    }

}
