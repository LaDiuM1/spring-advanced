package study.spring_advanced.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.spring_advanced.trace.TraceStatus;
import study.spring_advanced.trace.hellotrace.TraceV1;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final TraceV1 trace;

    public void save(String itemId, TraceStatus traceStatus) {
        TraceStatus nextStatus = traceStatus;

        try {
            nextStatus = trace.next(traceStatus, "OrderRepositoryV1.save");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("저장 예외 발생");
            }
            sleep(1000);
            trace.end(nextStatus);
        } catch (Exception e) {
            trace.exception(nextStatus, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
