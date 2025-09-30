package study.spring_advanced.proxy.config.proxy.v2.handler;

import org.springframework.util.PatternMatchUtils;
import study.spring_advanced.proxy.trace.TraceStatus;
import study.spring_advanced.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceInvocationHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace trace;
    private final String[] loggingPatterns;

    public LogTraceInvocationHandler(Object target, LogTrace trace, String[] loggingPatterns) {
        this.target = target;
        this.trace = trace;
        this.loggingPatterns = loggingPatterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 메서드명 패턴 기반 로그 적용 여부 필터링
        String methodName = method.getName();
        if(!PatternMatchUtils.simpleMatch(loggingPatterns, methodName)) {
            return method.invoke(target, args);
        }

        TraceStatus traceStatus = null;

        Object response;

        try {
            String callerName = method.getDeclaringClass().getSimpleName() + "." + methodName;

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
