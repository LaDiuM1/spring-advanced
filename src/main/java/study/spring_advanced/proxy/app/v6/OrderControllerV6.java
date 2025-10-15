package study.spring_advanced.proxy.app.v6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface OrderControllerV6 {

    @GetMapping("/v6/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v6/no-log")
    String noLog();

}
