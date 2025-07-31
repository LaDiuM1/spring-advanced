package study.spring_advanced.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {

    private final String id;
    private final int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // 로그용, 앞 8자리만 사용
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, this.level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, this.level - 1);
    }

    public boolean isFirstLevel() {
        return this.level == 0;
    }

}
