package study.spring_advanced.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;

    public OrderControllerV1(OrderServiceV1 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v1/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "OK";
    }

    @GetMapping("/v1/no-log")
    public String noLog() {
        return "OK";
    }

}
