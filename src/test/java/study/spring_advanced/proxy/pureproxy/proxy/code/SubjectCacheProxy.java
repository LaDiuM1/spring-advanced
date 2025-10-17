package study.spring_advanced.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubjectCacheProxy implements Subject {

    private final Subject target;
    private String cachedData = null;

    public SubjectCacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        return getData();
    }

    private String getData() {
        if (cachedData == null) {
            cachedData = target.operation();
        } else {
            log.info("캐시된 객체 호출");
        }

        return cachedData;
    }

}
