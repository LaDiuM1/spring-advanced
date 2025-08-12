package study.spring_advanced.trace.template;

import lombok.RequiredArgsConstructor;
import study.spring_advanced.trace.TraceStatus;
import study.spring_advanced.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public abstract class AbstractLogTraceTemplate {

    private final LogTrace trace;

    public void execute() {
        TraceStatus traceStatus = null;

        try {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
            String simpleClassName = ste.getClassName().substring(ste.getClassName().lastIndexOf(".") + 1);
            String callerName = simpleClassName + "." + ste.getMethodName();

            traceStatus = trace.begin(callerName);
            call();
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }

    public abstract void call();

}
