package study.spring_advanced.trace.logtrace;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.spring_advanced.trace.TraceStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ThreadLocalLogTraceTest {

    private ThreadLocalLogTrace logTrace;

    @BeforeEach
    void setUp() {
        logTrace = new ThreadLocalLogTrace();
    }

    @Test
    void begin_end() {
        // given
        String message = "hello";
        // when
        TraceStatus status = logTrace.begin(message);
        logTrace.end(status);
        // then
        assertThat(status.getMessage()).isEqualTo(message);
        assertThat(status.getTraceId()).isNotNull();
        assertThat(status.getTraceId().getLevel()).isZero();
    }

    @Test
    void begin_exception() {
        // given
        String message = "exception test";
        TraceStatus status = logTrace.begin(message);
        Exception ex = new IllegalStateException("ex");
        // when
        logTrace.exception(status, ex);
        // then
        assertThat(status.getMessage()).isEqualTo(message);
    }

    @Test
    void nested_trace() {
        // given
        TraceStatus status1 = logTrace.begin("outer");
        TraceStatus status2 = logTrace.begin("inner");
        // when
        logTrace.end(status2);
        logTrace.end(status1);
        // then
        assertThat(status2.getTraceId().getLevel()).isEqualTo(status1.getTraceId().getLevel() + 1);
    }

    @Test
    void traceId_reset_after_complete() {
        // given
        TraceStatus status1 = logTrace.begin("first");
        logTrace.end(status1);

        TraceStatus status2 = logTrace.begin("second");
        // then
        assertThat(status2.getTraceId().getLevel()).isZero();
    }

    @Test
    void thread_isolation() throws InterruptedException {
        // 메인 쓰레드의 trace
        TraceStatus mainStatus = logTrace.begin("main");

        // 새로운 스레드의 trace
        Runnable task = () -> {
            ThreadLocalLogTrace otherThreadLogTrace = new ThreadLocalLogTrace();
            TraceStatus status = otherThreadLogTrace.begin("other-thread");
            assertThat(status.getTraceId().getLevel()).isZero();
            otherThreadLogTrace.end(status);
        };

        Thread thread = new Thread(task);
        thread.start();
        thread.join();

        // 독립성 여부 확인
        assertThat(mainStatus.getTraceId().getLevel()).isZero();
        logTrace.end(mainStatus);
    }



}