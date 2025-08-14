package study.spring_advanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import study.spring_advanced.trace.logtrace.LogTrace;
import study.spring_advanced.trace.strategy.StrategyLogTrace;
import study.spring_advanced.trace.template.AbstractLogTraceTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final StrategyLogTrace logTrace;

    public void save(String itemId) {
        logTrace.execute(() -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("저장 예외 발생");
            }
            sleep(1000);
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
