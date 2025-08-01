package study.spring_advanced.trace.hellotrace;

import org.junit.jupiter.api.Test;
import study.spring_advanced.trace.TraceStatus;

class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("trace_test");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("trace_test");
        trace.exception(status, new IllegalStateException());
    }

}