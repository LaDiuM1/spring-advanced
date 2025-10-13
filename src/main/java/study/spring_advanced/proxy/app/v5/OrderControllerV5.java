package study.spring_advanced.proxy.app.v5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface OrderControllerV5 {

    @GetMapping("/v5/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v5/no-log")
    String noLog();

}
