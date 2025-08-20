package study.spring_advanced.embedded_patterns.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import study.spring_advanced.embedded_patterns.trace.strategy.code.strategy.ContextV2;

@Slf4j
public class ContextV2Test {

    /**
     * 파라미터 기반 전략 패턴
     */
    @Test
    void strategy_parameter() {
        ContextV2 context1 = new ContextV2();
        context1.execute(() -> log.info("비즈니스 로직 1 실행"));

        ContextV2 context2 = new ContextV2();
        context2.execute(() -> log.info("비즈니스 로직 2 실행"));
    }

}
