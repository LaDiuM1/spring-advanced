package study.spring_advanced.embedded_patterns.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.spring_advanced.embedded_patterns.trace.strategy.StrategyLogTrace;

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
