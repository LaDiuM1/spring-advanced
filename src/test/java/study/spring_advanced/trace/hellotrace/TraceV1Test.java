package study.spring_advanced.trace.hellotrace;

import org.junit.jupiter.api.Test;
import study.spring_advanced.trace.TraceStatus;

class TraceV1Test {

    @Test
    void begin_end() {
        TraceV1 trace = new TraceV1();
        TraceStatus status = trace.begin("trace_test");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        TraceV1 trace = new TraceV1();
        TraceStatus status = trace.begin("trace_test");
        trace.exception(status, new IllegalStateException());
    }

}