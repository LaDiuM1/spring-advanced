package study.spring_advanced.embedded_patterns.trace.logtrace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import study.spring_advanced.embedded_patterns.trace.TraceId;
import study.spring_advanced.embedded_patterns.trace.TraceStatus;

@Slf4j
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private TraceId TraceIdHolder;

    @Override
    public TraceStatus begin(String message) {
        nextHoldTraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", TraceIdHolder.getId(), addSpace(START_PREFIX, TraceIdHolder.getLevel()), message);
        return new TraceStatus(TraceIdHolder, startTimeMs, message);
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        if (e == null) {
            log.info("[{}] {}{} time={}ms",
                    TraceIdHolder.getId(),
                    addSpace(COMPLETE_PREFIX, TraceIdHolder.getLevel()),
                    status.getMessage(),
                    resultTimeMs
            );
        } else {
            log.info("[{}] {}{} time={}ms ex={}",
                    TraceIdHolder.getId(), addSpace(EX_PREFIX, TraceIdHolder.getLevel()),
                    status.getMessage(),
                    resultTimeMs,
                    e.toString()
            );
        }
        previousHoldTraceId();
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }

    private void nextHoldTraceId() {
        if(TraceIdHolder == null) {
            TraceIdHolder = new TraceId();
        } else {
            TraceIdHolder = TraceIdHolder.createNextId();
        }
    }

    private void previousHoldTraceId() {
        if(TraceIdHolder.isFirstLevel()) {
            TraceIdHolder = null;
        } else {
            TraceIdHolder = TraceIdHolder.createPreviousId();
        }
    }
}
