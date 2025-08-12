package study.spring_advanced.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.spring_advanced.app.v2.OrderRepositoryV2;
import study.spring_advanced.trace.TraceStatus;
import study.spring_advanced.trace.logtrace.LogTrace;
import study.spring_advanced.trace.template.AbstractLogTraceTemplate;

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
