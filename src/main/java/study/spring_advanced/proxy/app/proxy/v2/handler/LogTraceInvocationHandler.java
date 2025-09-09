package study.spring_advanced.proxy.app.proxy.v2.handler;

import study.spring_advanced.proxy.trace.TraceStatus;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceInvocationHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace trace;

    public LogTraceInvocationHandler(Object target, LogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus traceStatus = null;

        Object response;

        try {
            String callerName = method.getDeclaringClass().getSimpleName() + "." + method.getName();

            traceStatus = trace.begin(callerName);
            response = method.invoke(target, args);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }

        return response;
    }
}
