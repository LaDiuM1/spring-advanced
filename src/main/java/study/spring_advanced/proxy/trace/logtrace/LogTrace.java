package study.spring_advanced.proxy.trace.logtrace;


import study.spring_advanced.proxy.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);

}
