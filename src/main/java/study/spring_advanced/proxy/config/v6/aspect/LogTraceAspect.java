package study.spring_advanced.proxy.config.v6.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import study.spring_advanced.proxy.trace.TraceStatus;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

@Aspect
public class LogTraceAspect {

    private final LogTrace trace;

    public LogTraceAspect(LogTrace trace) {
        this.trace = trace;
    }

    @Around("""
                execution(* study.spring_advanced.proxy.app.v6..*(..)) &&
                !execution(* study.spring_advanced.proxy.app.v6..noLog(..))
            """)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus traceStatus = null;

        Object response;
        try {
            String message = joinPoint.toShortString();
            traceStatus = trace.begin(message);
            response = joinPoint.proceed();
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }

        return response;
    }
}
