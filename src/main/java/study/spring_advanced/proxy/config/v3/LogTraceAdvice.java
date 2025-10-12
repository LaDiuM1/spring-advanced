package study.spring_advanced.proxy.config.v3;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import study.spring_advanced.proxy.trace.TraceStatus;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.Method;

public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace trace;

    public LogTraceAdvice(LogTrace trace) {
        this.trace = trace;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus traceStatus = null;

        Object response;
        try {
            Method method = invocation.getMethod();
            String callerName = method.getDeclaringClass().getSimpleName() + "." + method.getName();

            traceStatus = trace.begin(callerName);
            response = invocation.proceed();
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }

        return response;
    }
}
