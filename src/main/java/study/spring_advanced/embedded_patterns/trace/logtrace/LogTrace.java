package study.spring_advanced.embedded_patterns.trace.logtrace;

import study.spring_advanced.embedded_patterns.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);

}
