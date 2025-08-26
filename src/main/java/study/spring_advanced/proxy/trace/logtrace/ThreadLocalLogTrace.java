package study.spring_advanced.proxy.trace.logtrace;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import study.spring_advanced.proxy.trace.TraceId;
import study.spring_advanced.proxy.trace.TraceStatus;

@Slf4j
@Component
@Primary
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private final ThreadLocal<TraceId> localTraceId = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        nextHoldTraceId();
        TraceId traceId = localTraceId.get();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
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
        TraceId traceId = localTraceId.get();
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        if (e == null) {
            log.info("[{}] {}{} time={}ms",
                    traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                    status.getMessage(),
                    resultTimeMs
            );
        } else {
            log.info("[{}] {}{} time={}ms ex={}",
                    traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()),
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
        TraceId traceId = localTraceId.get();
        if(traceId == null) {
            localTraceId.set(new TraceId());
        } else {
            localTraceId.set(traceId.createNextId());
        }
    }

    private void previousHoldTraceId() {
        TraceId traceId = localTraceId.get();
        if(traceId.isFirstLevel()) {
            localTraceId.remove();
        } else {
            localTraceId.set(traceId.createPreviousId());
        }
    }
}
