package study.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {

    private final Component target;

    public TimeDecorator(Component target) {
        this.target = target;
    }

    @Override
    public String operation() {
        long startTime = System.currentTimeMillis();
        String result = target.operation();
        long endTime = System.currentTimeMillis();
        log.info("프로그램 실행 시간 : {}ms", endTime - startTime);

        return result;
    }
}
