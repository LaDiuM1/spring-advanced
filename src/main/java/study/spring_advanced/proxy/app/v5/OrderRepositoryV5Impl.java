package study.spring_advanced.proxy.app.v5;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5Impl implements OrderRepositoryV5 {

    @Override
    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("저장 예외 발생");
        }
        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
