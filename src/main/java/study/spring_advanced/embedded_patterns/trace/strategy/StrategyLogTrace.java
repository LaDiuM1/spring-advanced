package study.spring_advanced.embedded_patterns.trace.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.spring_advanced.embedded_patterns.trace.TraceStatus;
import study.spring_advanced.embedded_patterns.trace.logtrace.LogTrace;

@Component
@RequiredArgsConstructor
public class StrategyLogTrace {

    private final LogTrace trace;

    public void execute(Strategy strategy) {
        TraceStatus traceStatus = null;

        try {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
            String simpleClassName = ste.getClassName().substring(ste.getClassName().lastIndexOf(".") + 1);
            String callerName = simpleClassName + "." + ste.getMethodName();

            traceStatus = trace.begin(callerName);
            strategy.call();
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }

}
