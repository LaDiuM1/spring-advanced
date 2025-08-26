package study.spring_advanced.proxy.trace.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.spring_advanced.proxy.trace.TraceStatus;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

@Component
@RequiredArgsConstructor
public class StrategyLogTrace {

    private final LogTrace trace;

    public <T> T execute(Strategy<T> strategy) {
        TraceStatus traceStatus = null;

        T response;
        try {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
            String simpleClassName = ste.getClassName().substring(ste.getClassName().lastIndexOf(".") + 1);
            String callerName = simpleClassName + "." + ste.getMethodName();

            traceStatus = trace.begin(callerName);
            response = strategy.call();
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }

        return response;
    }

}
