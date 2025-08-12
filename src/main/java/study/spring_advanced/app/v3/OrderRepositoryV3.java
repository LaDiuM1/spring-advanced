package study.spring_advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.spring_advanced.trace.TraceStatus;
import study.spring_advanced.trace.logtrace.LogTrace;
import study.spring_advanced.trace.template.AbstractLogTraceTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId) {
        new AbstractLogTraceTemplate(trace) {
            @Override
            public void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("저장 예외 발생");
                }
                sleep(1000);
            }
        }.execute();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
