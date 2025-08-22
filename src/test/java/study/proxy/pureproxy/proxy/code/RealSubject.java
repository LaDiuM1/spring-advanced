package study.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {

    @Override
    public String operation() {
        log.info("실제 객체 호출");
        sleep(1000); // 객체 호출 시 1초가 걸린다고 가정(큰 부하)
        return "data";
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
