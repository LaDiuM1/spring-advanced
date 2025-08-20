package study.spring_advanced.embedded_patterns.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring_advanced.embedded_patterns.trace.logtrace.LogTrace;
import study.spring_advanced.embedded_patterns.trace.template.AbstractLogTraceTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        new AbstractLogTraceTemplate(trace) {
            @Override
            public void call() {
                orderRepository.save(itemId);
            }
        }.execute();
    }

}
