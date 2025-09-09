package study.spring_advanced.embedded_patterns.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.spring_advanced.embedded_patterns.trace.TraceStatus;
import study.spring_advanced.embedded_patterns.trace.logtrace.LogTrace;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final LogTrace trace;

    public void save(String itemId) {
        TraceStatus traceStatus = null;

        try {
            traceStatus = trace.begin("OrderRepositoryImplV2.save");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("저장 예외 발생");
            }
            sleep(1000);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
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
