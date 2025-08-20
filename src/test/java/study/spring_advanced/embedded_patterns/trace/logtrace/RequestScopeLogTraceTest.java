package study.spring_advanced.embedded_patterns.trace.logtrace;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.spring_advanced.embedded_patterns.trace.TraceStatus;

import static org.assertj.core.api.Assertions.assertThat;

class RequestScopeLogTraceTest {

    private RequestScopeLogTrace logTrace;

    @BeforeEach
    void setUp() {
        logTrace = new RequestScopeLogTrace();
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
}